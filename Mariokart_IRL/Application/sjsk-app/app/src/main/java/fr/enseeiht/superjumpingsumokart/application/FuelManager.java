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
    private float lastFuelLevel;

    /**
     * Current pit stop marker if any.
     */
    private DetectionTask.Symbol currentPitStopMarker;

    /**
     * Boolean indicating if the drone was refueling last frame.
     */
    private boolean wasRefuelingLastFrame;

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
     * @param speed the current speed of the drone (0-100).
     */
    public void updateFuelConsumption(float speed) {
        if (drone != null && game != null) {
            // Consume fuel based on speed
            drone.consumeFuel(speed);

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
     */
    private void checkFuelLevelChange() {
        float currentFuel = drone.getCurrentFuel();
        float fuelPercentage = (currentFuel / 100.0f) * 100.0f; // Assuming max fuel is normalized

        // Notify on fuel level change (every 1% change)
        if (Math.abs(currentFuel - lastFuelLevel) >= 1.0f) {
            lastFuelLevel = currentFuel;
            game.onFuelLevelChanged(fuelPercentage);

            // Check for critical fuel
            if (!drone.canMoveAtFullSpeed() && currentFuel <= 10.0f) {
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
