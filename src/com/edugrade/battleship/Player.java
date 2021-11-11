package com.edugrade.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private static GameMap map = new GameMap();
    private static Scanner scanner = new Scanner(System.in);

    private ArrayList<Boats> fleet;

    public Player() {
        this.fleet = new ArrayList<>();
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
                    map.setIndexValue("@", 1, number+1);
                    break;
                case "B":
                    map.setIndexValue("@", 2, number+1);
                    break;
                case "C":
                    map.setIndexValue("@", 3, number+1);
                    break;
                case "D":
                    map.setIndexValue("@", 4, number+1);
                    break;
                case "E":
                    map.setIndexValue("@", 5, number+1);
                    break;
                case "F":
                    map.setIndexValue("@", 6, number+1);
                    break;
                case "G":
                    map.setIndexValue("@", 7, number+1);
                    break;
                case "H":
                    map.setIndexValue("@", 8, number+1);
                    break;
                case "I":
                    map.setIndexValue("@", 9, number+1);
                    break;
                case "J":
                    map.setIndexValue("@", 10, number+1);
                    break;
            }
        }
    }

    public int[] shoot(){
        int[] shot = new int[2];
        System.out.print("Skriv in y-koordinat: ");
        shot[0] = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Skriv in en x-koordinat: ");
        shot[1] = scanner.nextInt();
        scanner.nextLine();
        return shot;
    }

    public String getShot(int[] shots) {

        String value = map.getIndexValue(shots[0], shots[1]);

        if (value.equals("@")) {
            map.setIndexValue("$", shots[0], shots[1]);
        } else {
            map.setIndexValue("X", shots[0], shots[1]);
        }
        return value;
    }

    public void checkHit(int[] shots) {
        String value = map.getIndexValue(shots[0], shots[1]);

        if (value.equals("$")) {
            System.out.println("Datorn träffade!");
        } else {
            System.out.println("Datorn missade!");
        }
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
        map.drawMap();
    }

    /**
     * This method prints the Player game map to the terminal.
     * @author Joachim Forsberg
     * */
    public void printPlayerMap() {
        map.printMap();
    }
}
