package fr.enseeiht.superjumpingsumokart.application.circuit;

import java.util.ArrayList;
import java.util.HashMap;

import fr.enseeiht.superjumpingsumokart.application.items.Item;
import fr.enseeiht.superjumpingsumokart.application.items.PitStop;
import fr.enseeiht.superjumpingsumokart.arpack.DetectionTask;

/**
 * Represents a circuit in Super Jumping Sumo Kart.
 * Borders of the track are materialised by ARToolkit markers. The Circuit class is a singleton class.
 * @author Vivian Guy.
 */
public class Circuit {

    /**
     * The name of the circuit.
     */
    private String name;

    /**
     * The markers present on the circuit. Each marker is defined by its order in the circuit and its {@link fr.enseeiht.superjumpingsumokart.arpack.DetectionTask.Symbol}.
     */
    private ArrayList<DetectionTask.Symbol> markers;

    /**
     * The number of laps a player has to do to complete the circuit.
     */
    private int lapsNumber;

    /**
     * The number of checkPoint to check to complete a circuit's lap.
     */
    private int checkPointToCheck;

    /**
     * The markers containing an item on the circuit.
     */
    private HashMap<DetectionTask.Symbol,Item> objects;

    /**
     * The pit stops (fuel refill stations) on the circuit.
     */
    private HashMap<DetectionTask.Symbol, PitStop> pitStops;

    /**
     * Singleton instance of {@link Circuit}.
     */
    private static Circuit circuitInstance;

    /**
     * Constructor for singleton circuit.
     * @param laps The number of laps player will have to perform.
     * @param checkPointToCheck The number of checkpoints between each lap.
     */
    private Circuit(int laps, int checkPointToCheck) {
        this.lapsNumber = laps;
        this.checkPointToCheck = checkPointToCheck;
        this.markers = new ArrayList<>();
        this.objects = new HashMap<>();
        this.pitStops = new HashMap<>();
    }

    /**
     * Initialises the singleton instance of {@link Circuit}.
     * @param laps The number of laps for the circuit.
     * @param checkPointToCheck The number of check points to complete a lap.
     */
    public static void initInstance(int laps, int checkPointToCheck) {
        if (circuitInstance == null) {
            circuitInstance = new Circuit(laps, checkPointToCheck);
        }
    }

    /**
     * Adds markers to the list of markers present on the circuit.
     * @param symbol The symbol of the marker added.
     */
    public void addMarker(DetectionTask.Symbol symbol) {
        // Markers are ordered thanks to the key
        this.markers.add(symbol);
    }

    /**
     * Adds an object to the list of objects present on the circuit.
     * @param symbol The symbol of the marker.
     * @param item The item to put on the circuit.
     */
    public void addObject(DetectionTask.Symbol symbol,Item item){
        this.objects.put(symbol,item);
    }

    /**
     * Removes an object from the list of objects present on the circuit.
     * @param symbol The symbol of the marker associated to the object to remove.
     */
    public void removeObject(DetectionTask.Symbol symbol){ this.objects.remove(symbol); }

    /**
     * Adds a pit stop to the circuit.
     * @param symbol The marker symbol of the pit stop.
     * @param pitStop The pit stop to add.
     */
    public void addPitStop(DetectionTask.Symbol symbol, PitStop pitStop) {
        this.pitStops.put(symbol, pitStop);
    }

    /**
     * Gets a pit stop by its marker symbol.
     * @param symbol The marker symbol of the pit stop.
     * @return The pit stop, or null if not found.
     */
    public PitStop getPitStop(DetectionTask.Symbol symbol) {
        return this.pitStops.get(symbol);
    }

    /**
     * Check if a marker corresponds to a pit stop.
     * @param symbol The marker symbol to check.
     * @return true if the marker is a pit stop, false otherwise.
     */
    public boolean isPitStop(DetectionTask.Symbol symbol) {
        return this.pitStops.containsKey(symbol);
    }

    /**
     * Get all pit stops on the circuit.
     * @return HashMap of all pit stops.
     */
    public HashMap<DetectionTask.Symbol, PitStop> getPitStops() {
        return pitStops;
    }


    //   GETTER AND SETTER

    /**
     * @return The singleton instance of {@link Circuit}.
     */
    public static Circuit getInstance() {
        return circuitInstance;
    }

    /**
     * @return The name of the circuit.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The list of markers of the circuit.
     */
    public ArrayList<DetectionTask.Symbol> getMarkers() {
        return markers;
    }

    /**
     * @return The number of laps required to complete the circuit.
     */
    public int getLaps() {
        return lapsNumber;
    }

    /**
     * @return The number of checkpoints.
     */
    public int getCheckpointToCheck() {
        return checkPointToCheck;
    }

    /**
     * @return The list of {@link Item} present on the circuit.
     */
    public HashMap<DetectionTask.Symbol, Item> getObjects() {
        return objects;
    }


    /**
     * @param name The name of the circuit.
     */
    public void setName(String name) {
        this.name = name;
    }
}