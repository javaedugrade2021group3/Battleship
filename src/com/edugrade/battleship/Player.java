package com.edugrade.battleship;

import java.util.*;

public class Player {

    private static Random random = new Random();

    private ArrayList<Boats> fleet;
    private GameMap map;
    private ArrayList<String> hits;
    private ArrayList<String> shots;
    private ArrayList<String> closeHitShots;
    private Set<String> generatedShots;

    public Player() {
        this.fleet = new ArrayList<>();
        this.map = new GameMap();
        this.hits = new ArrayList<>();
        this.shots = new ArrayList<>();
        this.closeHitShots = new ArrayList<>();
        this.generatedShots = new LinkedHashSet<>();
    }

    /**
     * This method prints the player's shot queue (can be deleted before turning in)
     * @author Joachim Forsberg
     * */
    public void printShotQueue() {
        this.closeHitShots.forEach(e -> System.out.println(e));
    }

    /**
     * This method clears the content in the closeHitShots Array
     * @author Joachim Forsberg
     * */
    public void clearCloseHitArray() {
        this.closeHitShots.clear();
    }
    /**
     * This method returns the Ship ID if the ship get hit.
     * @author Joachim Forsberg
     * @param coordinates The coordinates of the hit.
     * */
    public int getShipID(String coordinates) {
        for (int i = 0; i < this.fleet.size(); i++) {
            if(this.fleet.get(i).getLocationOnMap().contains(coordinates.toUpperCase(Locale.ROOT))) {
                return this.fleet.get(i).getShipID();
            }
        }
        return 0;
    }
    /**
     * This method is used to decrease a hit point on a ship.
     * @author Joachim Forsberg
     * @param shipID The ship id.
     * */
    public int decrementShipLife(int shipID) {
        for (int i = 0; i < this.fleet.size(); i++) {
            if (this.fleet.get(i).getShipID() == shipID) {
                this.fleet.get(i).setNumOfSquares(this.fleet.get(i).getNumOfSquares() - 1);
                return this.fleet.get(i).getNumOfSquares();
            }
        }
        return 0;
    }

    /**
     * This method is used to check if a ship is active or if it's sunk.
     * @author Joachim Forsberg
     * @param shipID The ship id
     * */
    public String shipIsActive(int shipID) {
        for (int i = 0; i < this.fleet.size(); i++) {
            if (this.fleet.get(i).getShipID() == shipID) {
                if (this.fleet.get(i).getNumOfSquares() == 0) {
                    return "s";
                }
            }
        }
        return "h";
    }

    /**
     * This method is used inside shotCloseToLastHit method to add the coordinates for the boxes around
     * the last hit.
     * @author Joachim Forsberg
     * @param coordinates The coordinates for the new coordinate around the last hit
     * */
    private void generatedShotCoordinate(String coordinates) {
        int pos = this.shots.indexOf(coordinates);
        String shot = this.shots.get(pos);
        this.shots.remove(shot);
        this.closeHitShots.add(0, shot);
    }

    /**
     * This method is used inside shotCloseToLastHit method to return the coordinates for the boxes around
     * the last hit.
     * @author Joachim Forsberg
     * */
    private String getGeneratedShotCoordinateFromArray() {
        String shot = this.closeHitShots.get(0);
        this.closeHitShots.remove(shot);
        return shot;
    }

    /**
     * This method is used to get the surrounding coordinates on a hit.
     * Currently, it adds the hits in front of an ArrayList to be picked first.
     *
     * @author Joachim Forsberg
     * @param coordinates coordinates from the hit
     * */
    public String shootCloseToLastHit(String coordinates) {

        int number = Integer.parseInt(coordinates.substring(1));
        String letter = coordinates.substring(0,1);
        String letters = "abcdefghij";

        int letterAtIndex = letters.indexOf(letter);

        // Övre vänstra hörnet
        if (letterAtIndex == 0 && number == 0) {

            String sameNumLetterPlusOne = number + Character.toString((letters.charAt(letterAtIndex + 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));

            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (this.shots.contains(sameNumLetterPlusOne)) {
                generatedShotCoordinate(sameNumLetterPlusOne);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Nedre vänstra hörnet
        if (letterAtIndex == 9 && number == 0) {
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Mitten vänstra kanten
        if (letterAtIndex > 0 && letterAtIndex < 9 && number == 0) {
            String sameNumLetterPlusOne = number + Character.toString((letters.charAt(letterAtIndex + 1)));
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));

            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (this.shots.contains(sameNumLetterPlusOne)) {
                generatedShotCoordinate(sameNumLetterPlusOne);
            }
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Mitten övre kanten
        if (letterAtIndex == 0 && number > 0 && number < 9) {
            String sameNumLetterPlusOne = number + Character.toString((letters.charAt(letterAtIndex + 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (this.shots.contains(sameNumLetterPlusOne)) {
                generatedShotCoordinate(sameNumLetterPlusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Övre högra hörnet
        if (letterAtIndex == 0 && number == 9) {
            String sameNumLetterPlusOne = number + Character.toString((letters.charAt(letterAtIndex + 1)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterPlusOne)) {
                generatedShotCoordinate(sameNumLetterPlusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Nedre högra hörnet
        if (letterAtIndex == 9 && number == 9) {
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Mitten högra kanten
        if (letterAtIndex > 0 && letterAtIndex < 9 && number == 9) {
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }
        // Mitten nedre kanten
        if (letterAtIndex == 9 && number > 0 && number < 9) {
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }

        // Inte på kanterna
        if (letterAtIndex > 0 && letterAtIndex < 9 && number > 0 && number < 9) {
            String sameNumLetterPlusOne = number + Character.toString((letters.charAt(letterAtIndex + 1)));
            String sameNumLetterMinusOne = number + Character.toString((letters.charAt(letterAtIndex - 1)));
            String numPlusOneSameLetter = (number + 1) + Character.toString((letters.charAt(letterAtIndex)));
            String numMinusOneSameLetter = (number - 1) + Character.toString((letters.charAt(letterAtIndex)));
            if (this.shots.contains(sameNumLetterMinusOne)) {
                generatedShotCoordinate(sameNumLetterMinusOne);
            }
            if (this.shots.contains(sameNumLetterPlusOne)) {
                generatedShotCoordinate(sameNumLetterPlusOne);
            }
            if (this.shots.contains(numMinusOneSameLetter)) {
                generatedShotCoordinate(numMinusOneSameLetter);
            }
            if (this.shots.contains(numPlusOneSameLetter)) {
                generatedShotCoordinate(numPlusOneSameLetter);
            }
            if (!this.closeHitShots.isEmpty()) {
                return getGeneratedShotCoordinateFromArray();
            }
        }
        return generateShot();
    }

    /**
     * This method is used to create Boat objects and add them to the fleet list.
     * @author Joachim Forsberg
     * */
    public void createFleet() {
        fleet.add(Boats.createBoat(1,"Carrier", 5, new ArrayList<>(Arrays.asList("D4", "E4", "F4", "G4", "H4"))));
        fleet.add(Boats.createBoat(2,"Battleship", 4, new ArrayList<>(Arrays.asList("A4", "A5", "A6", "A7"))));
        fleet.add(Boats.createBoat(3,"Battleship", 4, new ArrayList<>(Arrays.asList("J0", "J1", "J2", "J3"))));
        fleet.add(Boats.createBoat(4,"Cruiser", 3, new ArrayList<>(Arrays.asList("D2", "E2", "F2"))));
        fleet.add(Boats.createBoat(5,"Cruiser", 3, new ArrayList<>(Arrays.asList("C6", "D6", "E6"))));
        fleet.add(Boats.createBoat(6,"Cruiser", 3, new ArrayList<>(Arrays.asList("J5", "J6", "J7"))));
        fleet.add(Boats.createBoat(7,"Submarine", 2, new ArrayList<>(Arrays.asList("H9", "I9"))));
        fleet.add(Boats.createBoat(8,"Submarine", 2, new ArrayList<>(Arrays.asList("C9", "D9"))));
        fleet.add(Boats.createBoat(9,"Submarine", 2, new ArrayList<>(Arrays.asList("G6", "G7"))));
        fleet.add(Boats.createBoat(10,"Submarine", 2, new ArrayList<>(Arrays.asList("C0", "D0"))));
    }

    /**
     * This method is used to merge all the locations of the whole fleet in a single ArrayList.
     * @author Joachim Forsberg
     * */
    public ArrayList<String> getFleetLocation() {
        ArrayList<String> locations = new ArrayList<>();
        for (int i = 0; i < this.fleet.size(); i++) {
            locations.addAll(this.fleet.get(i).getLocationOnMap());
        }
        return locations;
    }

    /**
     * This method is used to place the fleet on the game map. Which
     * uses the locations from the Boat objects.
     * @author Joachim Forsberg
     * @param location Is the ArrayList of all the fleet's locations.
     * */
    public void placeFleetOnMap(ArrayList<String> location) {

        /** The Fleet's location
         * [-], 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         * [A],  ,  ,  ,  , @, @, @, @,  ,  ]
         * [B],  ,  ,  ,  ,  ,  ,  ,  ,  ,  ]
         * [C], @,  ,  ,  ,  ,  , @,  ,  , @]
         * [D], @,  , @,  , @,  , @,  ,  , @]
         * [E],  ,  , @,  , @,  , @,  ,  ,  ]
         * [F],  ,  , @,  , @,  ,  ,  ,  ,  ]
         * [G],  ,  ,  ,  , @,  , @, @,  ,  ]
         * [H],  ,  ,  ,  , @,  ,  ,  ,  , @]
         * [I],  ,  ,  ,  ,  ,  ,  ,  ,  , @]
         * [J], @, @, @, @,  , @, @, @,  ,  ]
         * */

        String letter = "";
        int number;

        for (int i = 0; i < location.size(); i++) {
            letter = location.get(i).substring(0,1);
            number = Integer.parseInt(location.get(i).substring(1));
            switch (letter) {
                case "A":
                    this.map.setIndexValue("@", 1, number+1);
                    break;
                case "B":
                    this.map.setIndexValue("@", 2, number+1);
                    break;
                case "C":
                    this.map.setIndexValue("@", 3, number+1);
                    break;
                case "D":
                    this.map.setIndexValue("@", 4, number+1);
                    break;
                case "E":
                    this.map.setIndexValue("@", 5, number+1);
                    break;
                case "F":
                    this.map.setIndexValue("@", 6, number+1);
                    break;
                case "G":
                    this.map.setIndexValue("@", 7, number+1);
                    break;
                case "H":
                    this.map.setIndexValue("@", 8, number+1);
                    break;
                case "I":
                    this.map.setIndexValue("@", 9, number+1);
                    break;
                case "J":
                    this.map.setIndexValue("@", 10, number+1);
                    break;
            }
        }
    }

    /**
     * This method is used to check if a ship got hit or not.
     * @author Joachim Forsberg
     * @param coordinates Coordinates of the shot
     * */
    public boolean checkShipPosition(String coordinates) {
        ArrayList<String> locations = new ArrayList<>();
        for (int i = 0; i < getFleetLocation().size(); i++) {
            locations.add(getFleetLocation().get(i).toLowerCase(Locale.ROOT));
        }
        return locations.contains(coordinates);
    }

    /**
     * This method is used to fill an ArrayList with shots from a LinkedHashSet
     * @author Joachim Forsberg
     * */
    public void fillShotsArray() {
        this.shots.addAll(this.generatedShots);
        Collections.shuffle(this.shots);
    }

    /**
     * This method is used to generate 100 shots
     * @author Joachim Forsberg
     * */
    public void generateShots() {
        String coordinateLetter = "abcdefghij";

        for (int i = 0; i < coordinateLetter.length(); i++) {
            for (int j = 0; j < coordinateLetter.length(); j++) {
                char letter = coordinateLetter.charAt(i);
                int number = j;
                this.generatedShots.add(number + Character.toString(letter));
            }
        }
    }


    /**
     * This method is used to draw a shot from the ArrayList
     * returns string Y + X  (A-J + 1-10)
     * @author Almen Novalic, Joachim Forsberg
     * */
    public String generateShot() {
        if (!this.closeHitShots.isEmpty()) {
            String shot = this.closeHitShots.get(0);
            this.closeHitShots.remove(shot);
            return shot;
        } else {
            String shot = this.shots.get(0);
            this.shots.remove(shot);
            return shot;
        }
    }

    public void printShots() {
        this.shots.forEach(e -> System.out.println(e));
    }

    /**
     * Sets opponents shot on out maps, returns hit or miss
     * @author Almen Novalic, Joachim Forsberg
     * @param shots Array of two index values for the map
     * */
    public String getShot(int[] shots) {

        String value = this.map.getIndexValue(shots[0], shots[1]);
        if (value.equals("@")) {
            this.map.setIndexValue("$", shots[0], shots[1]);
        }
        if (value.equals(" ")) {
            this.map.setIndexValue("X", shots[0], shots[1]);
        }
        return value;
    }

    /**
     * This method returns the coordinates from the previous hit
     * @author Joachim Forsberg
     * */
    public String getHitFromArray() {
        return this.hits.get(this.hits.size()-1);
    }

    /**
     * This method add the previous shot to a list, incase of a hit the coordinates
     * of the last hit is used for shooting close to the last hit.
     * @author Joachim Forsberg
     * @param shot The message from either Server or Client
     * */
    public void addHitToArray(String shot) {
        this.hits.add(shot);
    }

    /**
     * This method is used to generate the return message
     * @author Joachim Forsberg
     * @param hit Coordinates if a ship was hit
     * */
    public String getHitValue(boolean hit) {
        if (hit){
            return "h";
        }
        return "m";
    }

    /**
     * This method retuns the coordinates from the message
     * @author Joachim Forsberg
     * @param message The message that is sent between server and client
     * */
    public String getIncomingCoordinate(String message) {
        String coordinates = message.substring(7);
        String getLetter = coordinates.substring(0,1);
        String getNumber = coordinates.substring(1);
        return getNumber + getLetter;
    }

    /**
     * Converts opponent string to Shot
     * Returns int array
     * @author Almen Novalic
     * */
    public int[] convertOpponentShot(String inputString) {
        /**
         * Vår karta är 11x11, index 0 är upptaget.
         *  Vi lägger på +1 då vi kör på 1-10 och inte 0-9
         *  Gäller både x och y
         */

        int a[] = {0,0};
        int b = 0;
        String string = inputString.substring(0,1);

        if (string.equals("a")){b = 1;}  //Börjar på 1 istället för 0
        else if(string.equals("b")){b = 2;}
        else if (string.equals("c")){b = 3;}
        else if (string.equals("d")){b = 4;}
        else if (string.equals("e")){b = 5;}
        else if (string.equals("f")){b = 6;}
        else if (string.equals("g")){b = 7;}
        else if (string.equals("h")){b = 8;}
        else if (string.equals("i")){b = 9;}
        else if (string.equals("j")){b = 10;}

        int c = Integer.parseInt(inputString.substring(1));

        a[0] = b;
        a[1] = c + 1; //+1 för att hantera våra index korrekt

        return a;

    }

    /**
     * Builds message to opponent
     * input array, needs returnvalue for hit or miss
     * and coordinates for a new shot
     * @author Almen Novalic
     * */
    public String returnString(String checkHit, String coordinates) {
        return checkHit + " shot " + coordinates;
    }

    /**
     * Builds message to opponent for initial shot
     * @author Almen Novalic
     * */
    public String initialShot(){

     return returnString("i", generateShot());

    }

    /**
     * This method generates the Player game map.
     * @author Joachim Forsberg
     * */
    public void drawPlayerMap() {
        this.map.drawMap();
    }

    /**
     * This method prints the Player game map to the terminal.
     * @author Joachim Forsberg
     * */
    public void printPlayerMap() {
        this.map.printMap();
    }
}
