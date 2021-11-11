package com.edugrade.battleship;

/**
* Boat objects for the battleship game.
* This class provides the basic building blocks to create Boat objects.
* This class doesn't need to be instantiated because it's using a factory method to call the constructor.
* @author Joachim Forsberg
* @version 1.0
* @since 2021-11-10
* */

public class Boats {
    private String shipType;
    private int numOfSquares;
    private String[] locationOnMap;

    /**
     * Constructor
     * */
    public Boats(String shipType, int numOfSquares, String[] locationOnMap) {
        this.shipType = shipType;
        this.numOfSquares = numOfSquares;
        this.locationOnMap = locationOnMap;
    }

    /**
     * Getters
     * */
    public String[] getLocationOnMap() {
        return locationOnMap;
    }

    public String getShipType() {
        return shipType;
    }

    public int getNumOfSquares() {
        return numOfSquares;
    }

    /**
     * Factory method that calls the constructor, which allows us to create Boat
     * objects without the need to instantiate them.
     * */
    public static Boats createBoat(String shipType, int numOfSquares, String[] locationOnMap) {
        return new Boats(shipType, numOfSquares, locationOnMap);
    }
}
