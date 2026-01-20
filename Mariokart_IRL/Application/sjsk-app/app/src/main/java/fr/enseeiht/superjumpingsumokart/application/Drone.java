package fr.enseeiht.superjumpingsumokart.application;


import fr.enseeiht.superjumpingsumokart.application.items.*;

import fr.enseeiht.superjumpingsumokart.arpack.DetectionTask;

/**
 * Class representing a drone in the application.
 * It represents a drone as a player, not as a device.
 * @author Vivian Guy.
 */

public class Drone {

    /**
     * The current item the drone has.
     */
    private Item currentItem;

    /**
     * The current number of lap made by the drone on the {@link fr.enseeiht.superjumpingsumokart.application.circuit.Circuit#circuitInstance}.
     */
    private int currentLap;

    /**
     * The current number of checkpoint validated by the drone on the {@link fr.enseeiht.superjumpingsumokart.application.circuit.Circuit#circuitInstance}.
     */
    private int currentCheckpoint;

    /**
     * The last marker seen by the drone.
     */
    private DetectionTask.Symbol lastMarkerSeen;

    /**
     * The maximum fuel capacity of the drone.
     */
    private static final float MAX_FUEL = 100.0f;

    /**
     * The current fuel level of the drone.
     */
    private float currentFuel;

    /**
     * The fuel consumption rate per unit of speed.
     */
    private static final float FUEL_CONSUMPTION_RATE = 0.5f;

    /**
     * The fuel refill rate per time unit.
     */
    private static final float FUEL_REFILL_RATE = 2.0f;

    /**
     * Boolean to check if the drone is currently in a pit stop.
     */
    private boolean inPitStop = false;

    /**
     * The speed threshold below which the drone cannot move when fuel is critical.
     */
    private static final float CRITICAL_FUEL_THRESHOLD = 10.0f;

    /**
     * Constructor for the class {@link Drone}.
     */
    Drone() {
        this.currentItem = new NullItem();
        this.currentCheckpoint = 0;
        this.currentLap = 0;
        this.currentFuel = MAX_FUEL;
        this.inPitStop = false;
    }

    /**
     * Get the {@link Item} of the drone.
     * @return the current item of the drone.
     */
    public Item getCurrentItem() {
        return currentItem;
    }

    /**
     * Set the {@link Item} of the drone.
     * @param currentItem the new item of the drone.
     */
    public void setCurrentItem(Item currentItem) {

        this.currentItem = currentItem;
    }

    /**
     * @return the number of lap the drone has done.
     */
    public int getCurrentLap() {
        return currentLap;
    }

    /**
     * @param currentLap the number of lap the drone has done.
     */
    void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    /**
     *
     * @return The current number of checkpoints validated by the drone.
     */
    public int getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    /**
     * @param currentCheckpoint the number of checkpoint validated by the drone.
     */
    void setCurrentCheckpoint(int currentCheckpoint) {
        this.currentCheckpoint = currentCheckpoint;
    }

    /**
     * @return The marker last marker seen by the drone.
     */
    public DetectionTask.Symbol getLastMarkerSeen() {
        return lastMarkerSeen;
    }

    /**
     * @param lastMarkerSeen The marker to put as last marker seen.
     */
    public void setLastMarkerSeen(DetectionTask.Symbol lastMarkerSeen) {
        this.lastMarkerSeen = lastMarkerSeen;
    }

    /**
     * Get the current fuel level of the drone.
     * @return the current fuel level.
     */
    public float getCurrentFuel() {
        return currentFuel;
    }

    /**
     * Get the fuel level as a percentage (0-100).
     * @return the fuel percentage.
     */
    public float getFuelPercentage() {
        return (currentFuel / MAX_FUEL) * 100.0f;
    }

    /**
     * Set the current fuel level of the drone.
     * @param fuel the fuel level to set.
     */
    public void setCurrentFuel(float fuel) {
        this.currentFuel = Math.min(fuel, MAX_FUEL);
        this.currentFuel = Math.max(this.currentFuel, 0.0f);
    }

    /**
     * Consume fuel based on the drone's speed.
     * @param speed the current speed of the drone.
     */
    public void consumeFuel(float speed) {
        if (!inPitStop && currentFuel > 0) {
            float consumption = speed * FUEL_CONSUMPTION_RATE;
            currentFuel = Math.max(currentFuel - consumption, 0.0f);
        }
    }

    /**
     * Refill fuel when the drone is in a pit stop.
     */
    public void refillFuel() {
        if (inPitStop && currentFuel < MAX_FUEL) {
            currentFuel = Math.min(currentFuel + FUEL_REFILL_RATE, MAX_FUEL);
        }
    }

    /**
     * Check if the drone can move at full speed.
     * @return true if the drone has enough fuel, false otherwise.
     */
    public boolean canMoveAtFullSpeed() {
        return currentFuel > CRITICAL_FUEL_THRESHOLD;
    }

    /**
     * Check if the drone has no fuel at all.
     * @return true if the drone is out of fuel, false otherwise.
     */
    public boolean isOutOfFuel() {
        return currentFuel <= 0.0f;
    }

    /**
     * Check if the drone is in a pit stop.
     * @return true if the drone is in a pit stop, false otherwise.
     */
    public boolean isInPitStop() {
        return inPitStop;
    }

    /**
     * Set whether the drone is in a pit stop.
     * @param inPitStop true if the drone enters a pit stop, false otherwise.
     */
    public void setInPitStop(boolean inPitStop) {
        this.inPitStop = inPitStop;
    }

    /**
     * Reset the drone's fuel to the maximum for a new race.
     */
    public void resetFuel() {
        this.currentFuel = MAX_FUEL;
        this.inPitStop = false;
    }
}
