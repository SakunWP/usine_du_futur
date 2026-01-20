package fr.enseeiht.superjumpingsumokart.application;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

import com.parrot.arsdk.arcommands.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_ENUM;
import com.parrot.arsdk.arcontroller.*;
import com.parrot.arsdk.ardiscovery.*;
import com.parrot.arsdk.arcommands.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_ENUM;

import fr.enseeiht.superjumpingsumokart.application.items.NullItem;
import fr.enseeiht.superjumpingsumokart.arpack.DetectionTask;
import fr.enseeiht.superjumpingsumokart.arpack.GUIGame;

import static java.lang.Thread.sleep;

/**
 * This class is used as a controller of a Jumping Sumo device.
 * @author Matthieu Michel, Romain Verset.
 */
public class DroneController implements ARDeviceControllerListener, ARDeviceControllerStreamListener {

    /**
     * The logging tag. Useful for debugging.
     */
    private final static String DRONE_CONTROLLER_TAG = "DRONE_CONTROLLER";

    /**
     * Drone associated to the {@link DroneController}.
     */
    private final Drone DRONE;

    /**
     * Graphic user interface of the game, this is the interface displayed during a race.
     */
    private final GUIGame GUI_GAME;

    /**
     * Controller associated to the device.
     */
    private ARDeviceController deviceController;

    // EnventListener pour le gyro et l'accelerometre
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeListener;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerListener;

    private SharedPreferences sharedPreferences;
    private boolean savedImageValue;

    // Speed constants
    private final static byte NO_SPEED = (byte) 10;
    private final static byte SLOW_SPEED = (byte) 10;
    private final static byte NEG_SLOW_SPEED = (byte) -5;
    private final static byte NORMAL_SPEED = (byte) 20;
    private final static byte NEG_NORMAL_SPEED = (byte) -20;
    private final static byte FAST_SPEED = (byte) 40;
    private final static byte NEG_FAST_SPEED = (byte) -40;
    private final static byte BOOST_SPEED = (byte) 100;

    private byte currentSpeed = NO_SPEED;

    /**
     * Indicates if the drone controller is started or not.
     */
    private boolean started = false;

    /**
     * Indicates if the drone controller is running or not.
     */
    private boolean running = false;

    /**
     * Indicates if the drone is moving forward or not.
     */
    private boolean movingForward = false;

    /**
     * Fps counter.
     */
    private int fps_count = 0;

    /**
     * Current forward speed of the drone.
     */
    private byte speedForward = NORMAL_SPEED;

    /**
     * Handler to evaluate the speed regarding the fuel state.
     */

    private Handler periodicSpeedHandler = new Handler(Looper.getMainLooper());

    /**
     * Runnable to run the action periodicSpeedHandler.
     */

    private Runnable periodicSpeedRunnable = new Runnable() {
        @Override
        public void run() {
            //Get the fuel state
            int fuel = DRONE.getFuel();
            if(fuel >= 90){
                speedForward = (movingForward) ? FAST_SPEED : NEG_FAST_SPEED;
            }else if (fuel == 0){
                speedForward = (movingForward) ? SLOW_SPEED : NEG_SLOW_SPEED;
            }else{
                speedForward = (movingForward) ? NORMAL_SPEED : NEG_NORMAL_SPEED;
            }
            Log.d(DRONE_CONTROLLER_TAG,"vitesse : "+speedForward);
            currentSpeed = speedForward;
            //Update the speed if the drone is moving forward
            //deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(speedForward);
            Log.d(DRONE_CONTROLLER_TAG,"vitesse env : "+speedForward);

            //Re-run the handler after 1 second
            //periodicSpeedHandler.postDelayed(this,1000);
        }
    };


    private boolean isInPit = false;
    private long pitTimeStart = 0;
    private static final long PIT_REFUEL_INTERVAL = 1000; // recherge tout les 1 seconde (1000 ms)

    private Runnable fuelRunnable;
    private Handler fuelHandler;
    private Runnable uiUpdateRunnable;
    private Handler uiUpdateHandler;

    /**
     * Default Constructor of the class {@link DroneController}.
     * It binds the {@link}.
     * @param guiGame interface of the Game.
     * @param device the device to create the controller for.
     */
    public DroneController(GUIGame guiGame, ARDiscoveryDevice device, Sensor gyroscopeSensor, Sensor accelerometerSensor) {
        GUI_GAME = guiGame;
        DRONE = new Drone();

        this.uiUpdateHandler = new Handler(Looper.getMainLooper());
        this.uiUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                GUI_GAME.updateFuelUI(DRONE.getFuel());
                Log.d(DRONE_CONTROLLER_TAG, "Fuel: "+DRONE.getFuel()+"\n");
                uiUpdateHandler.postDelayed(this, 500); // relance toutes les 500ms.
            }
        };

        // handler pour la gestion du fuel
        this.fuelHandler = new Handler(Looper.getMainLooper());
        this.fuelRunnable = new Runnable(){
            @Override
            public void run(){
                int decrease = 0;
                if (Math.abs(currentSpeed) == NORMAL_SPEED){
                    decrease = 5;
                } else if (Math.abs(currentSpeed) == FAST_SPEED){
                    decrease = 8;
                }
                if (decrease > 0){
                    DRONE.setFuel(DRONE.getFuel() - 0);
                    Log.d(DRONE_CONTROLLER_TAG, "Fuel decreased by "+decrease+". Current fuel: "+DRONE.getFuel());
                }
                fuelHandler.postDelayed(this, 1000); // relance toutes les secondes.
            }
        };

        this.gyroscopeSensor = gyroscopeSensor;
        try {
            deviceController = new ARDeviceController(device);
            deviceController.addListener(this);
            deviceController.addStreamListener(this);
        } catch (ARControllerException e) {
            Log.e(DRONE_CONTROLLER_TAG, "Unable to create device controller : " + e.getMessage());
        }

        // Traitement execution de l'accelerometre pour la direction
        gyroscopeListener = new SensorEventListener() {
            // Variables config
            private static final int MAX_WAIT = 50;
            private static final float TURN_ANGLE = 0.37f;
            private int i = 0;
            private float z;
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Considération periodique de la valeur , dès lors qu'on veut utiliser le mode gyro
                if(i>=MAX_WAIT && GUI_GAME.usingGyro){
                    Log.d("Gyroscope","z:"+sensorEvent.values[2]);
                    if(sensorEvent.values[2] < -TURN_ANGLE){
                        turnRight();
                        Log.d("Gyroscope","Droite");
                    } else if(sensorEvent.values[2] > TURN_ANGLE){
                        turnLeft();
                        Log.d("Gyroscope","Gauche");
                    } else {
                        stopRotation();
                        Log.d("Gyroscope","Ne tourne pas");
                    }
                    i=0;
                } else {
                    i++;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        guiGame.registerGyroscopeListener(gyroscopeListener);

        // Traitement execution de l'accelerometre pour le saut
        accelerometerListener = new SensorEventListener() {
            private int nbShake = 0;
            private long firstShakeDetected;
            private long lastJump = System.currentTimeMillis();
            private long lastUpdate = System.currentTimeMillis();
            private float last_x,last_y,last_z;
            private static final int SHAKE_THRESHOLD = 600;
            private static final int MAX_DELAY_JUMP_SHAKE = 500;
            private static final int JUMP_TIMEOUT = 5000; //MILLISECONDS (5000 = 5 seconds)
            private Vibrator v = (Vibrator) guiGame.getSystemService(Context.VIBRATOR_SERVICE);

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (accelerometerSensor != null) {
                    long curTime = System.currentTimeMillis();
                    // only allow one update every 200ms.
                    long diffTime = (curTime - lastUpdate);
                    if (diffTime > 200) {
                        lastUpdate = curTime;

                        float x = sensorEvent.values[0];
                        float y = sensorEvent.values[1];
                        float z = sensorEvent.values[2];

                        float speed = Math.abs(z - last_z) / diffTime * 10000;

                        Log.d("Accelerometer",""
                                +speed
                                +(speed > SHAKE_THRESHOLD?
                                    "/oui"+"/timeout:"+(curTime-lastJump)
                                            +"/nbShake:"+nbShake
                                            +"/delay:"+(curTime-firstShakeDetected)
                                :"/non")

                        );

                        if (speed > SHAKE_THRESHOLD) {
                            nbShake++;
                            //Saut à effectuer si deux shake détectés en une demi seconde (et timeout de jump fini)
                            if(nbShake>=2 && curTime-firstShakeDetected<MAX_DELAY_JUMP_SHAKE && curTime-lastJump>=JUMP_TIMEOUT) {
                                Log.d("Accelerometer","Jump activated");
                                longJump();
                                //Retour aptique pour annoncer le jump
                                long[] pattern = {200,500,200};
                                v.vibrate(pattern,2);

                                lastJump = System.currentTimeMillis();
                                nbShake = 0;
                                //Premier shake détecté = enregistrement de l'heure
                            } else if(nbShake == 1){
                                firstShakeDetected = System.currentTimeMillis();
                                Log.d("Accelerometer","First shake detected");
                            }
                        }

                        //Si on a dépassé le délai pour déclencher un jump on reset le nbShake
                        if(curTime-firstShakeDetected>=MAX_DELAY_JUMP_SHAKE){
                            nbShake = 0;
                            Log.d("Accelerometer","Shake reset, time over");
                        }
                        last_x = x;
                        last_y = y;
                        last_z = z;
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        guiGame.registerAccelerometerListener(accelerometerListener);
    }

    /**
     * Start the controller of the device.
     * @return The code resulting in the call of {@link ARDeviceController} stop() method on {@link #deviceController}.
     */
    public ARCONTROLLER_ERROR_ENUM startController() {
        ARCONTROLLER_ERROR_ENUM errCode = ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK;
        if (deviceController != null && !started) {
            fuelHandler.post(fuelRunnable);
            uiUpdateHandler.post(uiUpdateRunnable);
            errCode = deviceController.start();
        }
        return errCode;
    }

    /**
     * Stops the device controller by launching an external {@link Thread}.
     * It first stops the engine (speed equals to 0) and then disconnects the driver.
     */
    public ARCONTROLLER_ERROR_ENUM stopController() {
        ARCONTROLLER_ERROR_ENUM errCode = ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK;

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (deviceController != null && started && running) {
                    stopMotion();
                    fuelHandler.removeCallbacks(fuelRunnable);
                    uiUpdateHandler.removeCallbacks(uiUpdateRunnable);
                    deviceController.stop();
                }
            }
        }).start();
        return errCode;
    }

    /**
     * Makes the drone go forward with the constant speed.
     */
    public void moveForward() {
        Log.d(DRONE_CONTROLLER_TAG, "MOVE FORWARD order received !");
        if (deviceController != null && started) {
            movingForward = true;
            Log.d(DRONE_CONTROLLER_TAG, "MOVE FORWARD order received ! speed:"+NORMAL_SPEED);
            deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NORMAL_SPEED);
            //start the periodic speed handler.
           periodicSpeedHandler.post(periodicSpeedRunnable);
        }
    }

    /**
     *  Makes the drone go backward with the constant speed.
     */
    public void moveBackward() {
        if (deviceController != null && running) {
            movingForward = false;
            Log.d(DRONE_CONTROLLER_TAG, "MOVE BACKWARD order received !");
            periodicSpeedHandler.post(periodicSpeedRunnable);
            //deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NEG_NORMAL_SPEED);
        }
    }

    /**
     * Makes the drone turn left with the constant speed.
     */
    public void turnLeft() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "TURN LEFT order received !");
            deviceController.getFeatureJumpingSumo().setPilotingPCMDTurn(NEG_FAST_SPEED);
            this.currentSpeed = NEG_FAST_SPEED;
        }
    }

    /**
     * Makes the device turn right with the constant speed.
     */
    public void turnRight() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "TURN RIGHT order received !");
            deviceController.getFeatureJumpingSumo().setPilotingPCMDTurn(FAST_SPEED);
            this.currentSpeed = FAST_SPEED;
        }
    }

    /**
     * Stops the device.
     */
    public void stopMotion() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "STOP MOTION order received !");
            deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NO_SPEED);
            periodicSpeedHandler.removeCallbacks(periodicSpeedRunnable);
            this.currentSpeed = NO_SPEED;
        }
    }

    /**
     * Removes the rotation offset so that the device goes straight forward or backward.
     */
    public void stopRotation() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "STOP ROTATION order received !");
            deviceController.getFeatureJumpingSumo().setPilotingPCMDTurn(NO_SPEED);
        }
    }

    /**
     * Method used to use an Item.
     * Send a request to Item class to use the item owned by the player.
     */
    public boolean useItem(DetectionTask.Symbol symbol) {
        boolean used = false;
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "USE ITEM order received !");
            //check if there is an object of the marker
            if(! (DRONE.getCurrentItem() instanceof NullItem)) {
                used = DRONE.getCurrentItem().useItem(this, symbol);
                if (used) {
                    DRONE.setCurrentItem(new NullItem());
                }
            } else {
                Log.d(DRONE_CONTROLLER_TAG,"Item not used, Please Try again.");
            }
        }
        return used;
    }

    /**
     * Makes the drone jump high.
     */
    public void highJump() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "JUMP order received !");
            deviceController.getFeatureJumpingSumo().sendAnimationsJump(ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_ENUM.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_HIGH);
        }
    }

    /**
     * Makes the drone jump high.
     */
    public void longJump() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "JUMP order received !");
            deviceController.getFeatureJumpingSumo().sendAnimationsJump(ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_ENUM.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_LONG);
        }
    }

    /**
     * Boosts the speed of the drone for a short amount of time.
     */
    public void boost() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "JUMP order received !");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(BOOST_SPEED);
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    deviceController.getFeatureJumpingSumo().setPilotingPCMDSpeed(NORMAL_SPEED);
                }
            }).start();
        }
    }

    /**
     * Makes the drone spin.
     */
    public void spin() {
        if (deviceController != null && running) {
            Log.d(DRONE_CONTROLLER_TAG, "SPIN order received !");
            deviceController.getFeatureJumpingSumo().sendAnimationsSimpleAnimation(ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_ENUM.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_SPIN);
        }
    }

    /**
     * Makes the drone spin and highJump.
     */
    public void spinningJump() {
        if (deviceController != null && running) {
            deviceController.getFeatureJumpingSumo().sendAnimationsSimpleAnimation(ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_ENUM.ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_SPINJUMP);
        }
    }

    /**
     * Get the {@link Drone}.
     * @return the drone.
     */
    public Drone getDrone() {
        return DRONE;
    }

    /**
     * @return True if the {@link ARDeviceController} is running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Set the state of the race.
     * @param running the state of the race.
     */
    public void setRunning(boolean running) {
        this.running = running;
        deviceController.getFeatureJumpingSumo().setPilotingPCMDFlag((byte)1);
    }

    // Arrêt au stand
    public void enterPit(){
        isInPit = true;
        pitTimeStart = System.currentTimeMillis();
        Log.d(DRONE_CONTROLLER_TAG, "Le drone s'est arrêté au stand");
    }

    // Sortie du stand
    public void leavePit(){
        isInPit = false;
        Log.d(DRONE_CONTROLLER_TAG, "le drone a quitté le stand");
    }

    // Rempli le carburant si dans le stand et à l'arrêt
    public void updatePitRefueling() {
        if (isInPit && this.currentSpeed == 0) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - pitTimeStart >= PIT_REFUEL_INTERVAL) {
                DRONE.refillFuel(25); // rempli le réservoir au rythme de 25% par seconde
                pitTimeStart = currentTime;
                GUI_GAME.updateFuelUI(DRONE.getFuel());
                Log.d(DRONE_CONTROLLER_TAG, "Drone refueling... Fuel: " + DRONE.getFuel());
            }
        }
    }
    /**
     * Notify the user when there is a switch of state for the device.
     * @param deviceController controller associated to the device.
     * @param newState         new state of the drone (moving, turning, stop..).
     * @param error            type of the error.
     */
    @Override
    public void onStateChanged(ARDeviceController deviceController, ARCONTROLLER_DEVICE_STATE_ENUM newState, ARCONTROLLER_ERROR_ENUM error) {
        Log.d(DRONE_CONTROLLER_TAG, "State changed -> new state :" + newState + "| error: " + error);
        switch (newState) {
            case ARCONTROLLER_DEVICE_STATE_STARTING:
                started = true;
                break;
            case ARCONTROLLER_DEVICE_STATE_RUNNING :
                running = true;
                deviceController.getFeatureJumpingSumo().sendMediaStreamingVideoEnable((byte) 1);
                break;
            case ARCONTROLLER_DEVICE_STATE_STOPPING :
                deviceController.getFeatureJumpingSumo().sendMediaStreamingVideoEnable((byte) 0);
                deviceController.getFeatureJumpingSumo().setPilotingPCMDFlag((byte) 0);
                running = false;

                break;
            case ARCONTROLLER_DEVICE_STATE_PAUSED :
                running = false;
                break;
            case ARCONTROLLER_DEVICE_STATE_STOPPED :
                started = false;
                running = false;
                break;
            default:
                break;
        }
    }

    //When the extension state is changed.
    @Override
    public void onExtensionStateChanged(ARDeviceController deviceController, ARCONTROLLER_DEVICE_STATE_ENUM newState, ARDISCOVERY_PRODUCT_ENUM product, String name, ARCONTROLLER_ERROR_ENUM error) {
        //Nothing to do.
    }

    //When a signal is received from the device (low battery for instance).
    @Override
    public void onCommandReceived(ARDeviceController deviceController, ARCONTROLLER_DICTIONARY_KEY_ENUM commandKey, ARControllerDictionary elementDictionary) {
        //Nothing to do.
    }

    @Override
    public ARCONTROLLER_ERROR_ENUM configureDecoder(ARDeviceController deviceController, ARControllerCodec codec) {
        return ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK;
    }

    /**
     * Get the current frame of the video and send it to {@link #GUI_GAME} where the frame will be displayed.
     * @param deviceController controller associated to the device.
     * @param frame            current frame get from the drone.
     * @return ARCONTROLLER_OK if there is no problem (if display went well) otherwise ARCONTROLLER_ERROR_STREAM if there is a streaming problem.
     */
    @Override
    public ARCONTROLLER_ERROR_ENUM onFrameReceived(ARDeviceController deviceController, ARFrame frame) {
        fps_count++;
        if (fps_count%2 == 0) {
            if (!frame.isIFrame()) {
                return ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_ERROR_STREAM;
            }
            GUI_GAME.onFrameReceived(frame);
            fps_count = 0;
        }
        return ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK;
    }

    // When a frame is timedOut.
    @Override
    public void onFrameTimeout(ARDeviceController deviceController) {
        //Nothing to do.
    }

}
