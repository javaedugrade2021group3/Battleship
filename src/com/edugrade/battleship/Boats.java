package com.edugrade.battleship;

import java.util.ArrayList;

/**
 * Boat objects for the battleship game.
 * This class provides the basic building blocks to create Boat objects.
 * This class doesn't need to be instantiated because it's using a factory method to call the constructor.
 * @author Joachim Forsberg
 * @version 1.0
 * @since 2021-11-10
 * */

public class Boats {
    private int shipID;
    private String shipType;
    private int numOfSquares;
    private ArrayList<String> locationOnMap;

    /**
     * Constructor
     * */
    public Boats(int shipID, String shipType, int numOfSquares, ArrayList<String> locationOnMap) {
        this.shipID = shipID;
        this.shipType = shipType;
        this.numOfSquares = numOfSquares;
        this.locationOnMap = locationOnMap;
    }

    /**
     * Getters / Setters
     * */

    public ArrayList<String> getLocationOnMap() {
        return new ArrayList<>(locationOnMap);
    }

    public String getShipType() {
        return shipType;
    }

    public int getShipID() {
        return shipID;
    }

    public int getNumOfSquares() {
        return numOfSquares;
    }

    public void setNumOfSquares(int numOfSquares) {
        this.numOfSquares = numOfSquares;
    }

    /**
     * Factory method that calls the constructor, which allows us to create Boat
     * objects without the need to instantiate them.
     * @author Joachim Forsberg
     * */
    public static Boats createBoat(int shipID, String shipType, int numOfSquares, ArrayList<String> locationOnMap) {
        return new Boats(shipID, shipType, numOfSquares, locationOnMap);
    }
}
