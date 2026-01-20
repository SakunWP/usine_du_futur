package fr.enseeiht.superjumpingsumokart.application.items;

import fr.enseeiht.superjumpingsumokart.arpack.DetectionTask;

/**
 * Class representing a pit stop (fuel refill station) on the circuit.
 * Pit stops are special markers that allow the drone to refuel.
 * @author Game Development Team
 */
public class PitStop {

    /**
     * The marker symbol associated with this pit stop.
     */
    private DetectionTask.Symbol markerSymbol;

    /**
     * The position index of this pit stop on the circuit.
     */
    private int positionIndex;

    /**
     * Boolean to check if the drone is currently refueling at this pit stop.
     */
    private boolean isActive;

    /**
     * Constructor for the PitStop class.
     * @param markerSymbol the marker symbol of the pit stop.
     * @param positionIndex the position index on the circuit.
     */
    public PitStop(DetectionTask.Symbol markerSymbol, int positionIndex) {
        this.markerSymbol = markerSymbol;
        this.positionIndex = positionIndex;
        this.isActive = false;
    }

    /**
     * Get the marker symbol of this pit stop.
     * @return the marker symbol.
     */
    public DetectionTask.Symbol getMarkerSymbol() {
        return markerSymbol;
    }

    /**
     * Get the position index of this pit stop on the circuit.
     * @return the position index.
     */
    public int getPositionIndex() {
        return positionIndex;
    }

    /**
     * Check if this pit stop is currently active.
     * @return true if active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Set the active status of this pit stop.
     * @param active true to activate, false otherwise.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
