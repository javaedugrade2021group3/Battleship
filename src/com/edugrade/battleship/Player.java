package com.edugrade.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Player {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private ArrayList<Boats> fleet;
    private GameMap map;

    public Player() {
        this.fleet = new ArrayList<>();
        this.map = new GameMap();
    }

    /**
     * This method is used to create Boat objects and add them to the fleet list.
     * @author Joachim Forsberg
     * */
    public void createFleet() {
        fleet.add(Boats.createBoat("Carrier", 5, new String[] {"D4", "E4", "F4", "G4", "H4"}));
        fleet.add(Boats.createBoat("Battleship", 4, new String[] {"A4", "A5", "A6", "A7"}));
        fleet.add(Boats.createBoat("Battleship", 4, new String[] {"J0", "J1", "J2", "J3"}));
        fleet.add(Boats.createBoat("Cruiser", 3, new String[] {"D2", "E2", "F2"}));
        fleet.add(Boats.createBoat("Cruiser", 3, new String[] {"C6", "D6", "E6"}));
        fleet.add(Boats.createBoat("Cruiser", 3, new String[] {"J5", "J6", "J7"}));
        fleet.add(Boats.createBoat("Submarine", 2, new String[] {"H9", "I9"}));
        fleet.add(Boats.createBoat("Submarine", 2, new String[] {"C9", "D9"}));
        fleet.add(Boats.createBoat("Submarine", 2, new String[] {"G6", "G7"}));
        fleet.add(Boats.createBoat("Submarine", 2, new String[] {"C0", "D0"}));
    }

    /**
     * This method is used to merge all the locations of the whole fleet in a single ArrayList.
     * @author Joachim Forsberg
     * */
    public ArrayList<String> getFleetLocation() {
        ArrayList<String> locations = new ArrayList<>();
        for (int i = 0; i < this.fleet.size(); i++) {
            locations.addAll(Arrays.asList(this.fleet.get(i).getLocationOnMap()));
        }
        return locations;
    }

    /**
     * This is the updated method to place the fleet on the game map. Which
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
     * Randomize a shot
     * returns string Y + X  (A-J + 1-10)
     * @author Almen Novalic
     * */
    public String shoot(){

        String shotY = "";
        String shotX = "";
        int shot;

        shot = random.nextInt(9) + 0;
        shotY = String.valueOf(shot);

        shot = random.nextInt(9) + 0;
        if (shot == 0){shotX = "a";}
        else if (shot == 1){shotX = "b";}
        else if (shot == 2){shotX = "c";}
        else if (shot == 3){shotX = "d";}
        else if (shot == 4){shotX = "e";}
        else if (shot == 5){shotX = "f";}
        else if (shot == 6){shotX = "g";}
        else if (shot == 7){shotX = "h";}
        else if (shot == 8){shotX = "i";}
        else if (shot == 9){shotX = "j";}

        return shotY + shotX;

    }


    /**
     * Sets opponents shot on out maps, returns hit or miss
     * @author Almen Novalic
     * */
    public String getShot(int[] shots) {

        String value = this.map.getIndexValue(shots[0], shots[1]);
        String value2 = "";

        if (value.equals("@")) {
            this.map.setIndexValue("$", shots[0], shots[1]);
            value2 = "h";
        } else {
            this.map.setIndexValue("X", shots[0], shots[1]);
            value2 = "m";
        }
        return value2;
    }


    /**
     * Check if opponent has hit us.
     * Returns h or m.
     * @author Almen Novalic
     * */
    public String checkHit(int[] shots) {
        String value = this.map.getIndexValue(shots[0], shots[1]);

        if (value.equals("$")) {
            return "h";
        } else {
            return "m";
        }
    }


    /**
     * Check if we made a hit on opponent ship.
     * Returns h or m.
     * @author Almen Novalic
     * */
    public String checkIfWeHitOpponent(String inputString) {

        String result = inputString.substring(0,1);
        return result;
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
        String string = inputString.substring(8,9);

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

        int c = Integer.parseInt(inputString.substring(7,8));

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
    public String buildMessageToOpponent(String[] inputString ){

        String s = "";

        s = inputString[0] + " shot " + inputString[1];

        return s;


    }

    /**
     * Builds message to opponent for initial shot
     * @author Almen Novalic
     * */
    public String initialShot(){

     return buildMessageToOpponent(new String[]{"i", shoot()});


    }

    public void placeBoats(){

        /** Båtarnas placering
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

        /** Placerar ut båtar, MAP_01 **/

        /** Hangar x 1 **/
        map.setIndexValue("@",4,5);
        map.setIndexValue("@",5,5);
        map.setIndexValue("@",6,5);
        map.setIndexValue("@",7,5);
        map.setIndexValue("@",8,5);

        /** Slagskepp x 2 **/
        map.setIndexValue("@",1,5);
        map.setIndexValue("@",1,6);
        map.setIndexValue("@",1,7);
        map.setIndexValue("@",1,8);

        map.setIndexValue("@",10,1);
        map.setIndexValue("@",10,2);
        map.setIndexValue("@",10,3);
        map.setIndexValue("@",10,4);

        /** Kryssare x 3 **/
        map.setIndexValue("@",10,6);
        map.setIndexValue("@",10,7);
        map.setIndexValue("@",10,8);

        map.setIndexValue("@",4,3);
        map.setIndexValue("@",5,3);
        map.setIndexValue("@",6,3);

        map.setIndexValue("@",3,7);
        map.setIndexValue("@",4,7);
        map.setIndexValue("@",5,7);

        /** Ubåtar x 4 **/
        map.setIndexValue("@",7,7);
        map.setIndexValue("@",7,8);

        map.setIndexValue("@",8,10);
        map.setIndexValue("@",9,10);

        map.setIndexValue("@",3,1);
        map.setIndexValue("@",4,1);

        map.setIndexValue("@",3,10);
        map.setIndexValue("@",4,10);
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
