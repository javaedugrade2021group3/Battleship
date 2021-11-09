package com.edugrade.battleship;

import java.util.Scanner;

public class Player {
    private static GameMap map = new GameMap();
    private static Scanner scanner = new Scanner(System.in);

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

    public void drawPlayerMap() {
        map.drawMap();
    }

    public void printPlayerMap() {
        map.printMap();
    }
}
