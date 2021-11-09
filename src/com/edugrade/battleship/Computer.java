package com.edugrade.battleship;

import java.util.Random;
import java.util.Scanner;

public class Computer {
    private static GameMap map = new GameMap();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public int[] shoot() {

        int[] shot = {0, 0};
        for (int i = 0; i < 2; i++) {
            int randomNumber = random.nextInt(9) + 1;
            shot[i] = randomNumber;
        }

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

    public void checkHit (int[] shots) {

        String value = map.getIndexValue(shots[0], shots[1]);

        if (value.equals("$")) {
            System.out.println("Spelaren träffade!");
        } else {
            System.out.println("Spelaren missade!");
        }
    }

    public void placeBoats () {

        /** Båtarnas placering
         * [-], 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         * [A],  ,  ,  ,  , @, @, @, @,  ,  ]
         * [B],  ,  ,  ,  ,  ,  ,  ,  ,  ,  ]
         * [C],  ,  ,  ,  , @,  , @,  ,  , @]
         * [D], @,  , @,  , @,  , @,  ,  , @]
         * [E], @,  , @,  ,  ,  , @,  ,  ,  ]
         * [F], @,  , @,  ,  ,  ,  ,  ,  ,  ]
         * [G], @,  ,  ,  ,  ,  , @, @,  ,  ]
         * [H], @,  ,  ,  ,  ,  ,  ,  ,  , @]
         * [I],  ,  ,  ,  ,  ,  ,  ,  ,  , @]
         * [J], @, @, @, @,  , @, @, @,  ,  ]
         * */

        /** Placerar ut båtar, MAP_01 **/

        /** Hangar x 1 **/
        map.setIndexValue("@", 4, 1);
        map.setIndexValue("@", 5, 1);
        map.setIndexValue("@", 6, 1);
        map.setIndexValue("@", 7, 1);
        map.setIndexValue("@", 8, 1);

        /** Slagskepp x 2 **/
        map.setIndexValue("@", 1, 5);
        map.setIndexValue("@", 1, 6);
        map.setIndexValue("@", 1, 7);
        map.setIndexValue("@", 1, 8);

        map.setIndexValue("@", 10, 1);
        map.setIndexValue("@", 10, 2);
        map.setIndexValue("@", 10, 3);
        map.setIndexValue("@", 10, 4);

        /** Kryssare x 3 **/
        map.setIndexValue("@", 10, 6);
        map.setIndexValue("@", 10, 7);
        map.setIndexValue("@", 10, 8);

        map.setIndexValue("@", 4, 3);
        map.setIndexValue("@", 5, 3);
        map.setIndexValue("@", 6, 3);

        map.setIndexValue("@", 3, 7);
        map.setIndexValue("@", 4, 7);
        map.setIndexValue("@", 5, 7);

        /** Ubåtar x 4 **/
        map.setIndexValue("@", 7, 7);
        map.setIndexValue("@", 7, 8);

        map.setIndexValue("@", 8, 10);
        map.setIndexValue("@", 9, 10);

        map.setIndexValue("@", 3, 5);
        map.setIndexValue("@", 4, 5);

        map.setIndexValue("@", 3, 10);
        map.setIndexValue("@", 4, 10);
    }

    public void drawComputerMap () {
        map.drawMap();
    }

    public void printComputerMap () {
        map.printMap();
    }
}
