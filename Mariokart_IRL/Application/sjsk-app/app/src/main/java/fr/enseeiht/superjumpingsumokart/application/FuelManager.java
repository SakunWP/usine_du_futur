package fr.enseeiht.superjumpingsumokart.application;

import android.util.Log;

import fr.enseeiht.superjumpingsumokart.application.circuit.Circuit;
import fr.enseeiht.superjumpingsumokart.application.items.PitStop;
import fr.enseeiht.superjumpingsumokart.arpack.DetectionTask;

/**
 * Manager class for fuel system management during the race.
 * Handles fuel consumption, pit stop detection, and refueling.
 * @author Game Development Team
 */
public class FuelManager {

    /**
     * Logging tag for debugging.
     */
    private static final String FUEL_MANAGER_TAG = "FuelManager";

    /**
     * Reference to the game instance.
     */
    private Game game;

    /**
     * Reference to the drone.
     */
    private Drone drone;

    /**
     * Last fuel level to detect changes.
     */
    private int lastFuelLevel;

    /**
     * Current pit stop marker if any.
     */
    private DetectionTask.Symbol currentPitStopMarker;

    /**
     * Boolean indicating if the drone was refueling last frame.
     */
    private boolean wasRefuelingLastFrame;

    /**
     * Velocity threshold to detect immobility (for pit stop detection).
     */
    private static final float IMMOBILITY_THRESHOLD = 0.5f;

    /**
     * Constructor for FuelManager.
     * @param game the game instance.
     * @param drone the drone to manage fuel for.
     */
    public FuelManager(Game game, Drone drone) {
        this.game = game;
        this.drone = drone;
        this.lastFuelLevel = drone.getCurrentFuel();
        this.currentPitStopMarker = null;
        this.wasRefuelingLastFrame = false;
    }

    /**
     * Update fuel consumption based on current drone speed.
     * This should be called regularly during the game loop.
     * Speed-based consumption model:
     * - 5 units/frame at NORMAL_SPEED (20)
     * - 2 units/frame at REDUCED_SPEED (10)
     * - 0 units/frame at EMPTY_SPEED (5)
     * @param speed the current speed of the drone (20, 10, or 5).
     */
    public void updateFuelConsumption(byte speed) {
        if (drone != null && game != null) {
            Log.d(FUEL_MANAGER_TAG, " fuel a consumer");
            // Consume fuel based on speed
            drone.consumeFuel(speed);
            Log.d(FUEL_MANAGER_TAG, " fuel consumé" + drone.getCurrentFuel() );

            // Refuel if in pit stop
            if (drone.isInPitStop()) {
                drone.refillFuel();
                if (!wasRefuelingLastFrame) {
                    game.onPlayerEntersPitStop();
                }
                wasRefuelingLastFrame = true;
            } else {
                if (wasRefuelingLastFrame) {
                    game.onPlayerExitsPitStop();
                }
                wasRefuelingLastFrame = false;
            }

            // Check for fuel level changes
            checkFuelLevelChange();
        }
    }

    /**
     * Check if the drone is in a pit stop marker and update pit stop status.
     * This should be called when a marker is detected.
     * @param detectedMarker the marker detected by the drone.
     */
    public void checkMarkerForPitStop(DetectionTask.Symbol detectedMarker) {
        if (detectedMarker == null || Circuit.getInstance() == null) {
            return;
        }

        // Check if the detected marker is a pit stop
        if (Circuit.getInstance().isPitStop(detectedMarker)) {
            if (currentPitStopMarker == null || !currentPitStopMarker.equals(detectedMarker)) {
                // Entering a new pit stop
                currentPitStopMarker = detectedMarker;
                drone.setInPitStop(true);
                Log.d(FUEL_MANAGER_TAG, "Drone entering pit stop: " + detectedMarker.name());
                game.onPlayerEntersPitStop();
            }
        } else {
            // Not in a pit stop anymore
            if (currentPitStopMarker != null) {
                drone.setInPitStop(false);
                Log.d(FUEL_MANAGER_TAG, "Drone exiting pit stop: " + currentPitStopMarker.name());
                game.onPlayerExitsPitStop();
                currentPitStopMarker = null;
            }
        }
    }

    /**
     * Check if fuel level has changed significantly and notify listeners.
     * Works with integer fuel model (0-100).
     */
    private void checkFuelLevelChange() {
        int currentFuel = drone.getCurrentFuel();

        // Notify on fuel level change
        if (currentFuel != lastFuelLevel) {
            lastFuelLevel = currentFuel;
            game.onFuelLevelChanged(currentFuel);

            // Check for critical fuel (below 10)
            if (!drone.canMoveAtFullSpeed() && currentFuel <= 10 && currentFuel > 0) {
                game.onCriticalFuel();
            }
        }
    }

    /**
     * Reset the fuel manager for a new race.
     */
    public void reset() {
        drone.resetFuel();
        lastFuelLevel = drone.getCurrentFuel();
        currentPitStopMarker = null;
        wasRefuelingLastFrame = false;
    }

    /**
     * Get the current pit stop marker if any.
     * @return the current pit stop marker, or null if not in a pit stop.
     */
    public DetectionTask.Symbol getCurrentPitStopMarker() {
        return currentPitStopMarker;
    }
}
