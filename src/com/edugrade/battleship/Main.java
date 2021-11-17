package com.edugrade.battleship;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Player testPlayer = new Player(); // Test objekt
    private static Player testPlayer2 = new Player();

    public static void main(String[] args) {
        Menu.mainMenu();

        /**
         * Testar Båtobjekten.
         * */

        // Försvarare "server"
        testPlayer.drawPlayerMap(); // Genererar spelplanen
        testPlayer.createFleet(); // Skapar spelarens flotta
        testPlayer.placeFleetOnMap(testPlayer.getFleetLocation()); // Placerar flottan på spelplanen

        // Attackerare "klient"
        testPlayer2.drawPlayerMap();
        testPlayer2.createFleet();
        testPlayer2.placeFleetOnMap(testPlayer2.getFleetLocation());


        // Skott 1
        String initShot = testPlayer2.initialShot();
        System.out.println("testPlayer2 skjuter: " + initShot);
        testPlayer.getShot(testPlayer.convertOpponentShot(initShot));
        String checkHit = testPlayer.checkHit(testPlayer.convertOpponentShot(initShot));

        // Skott 2
        String pOneShot = testPlayer.buildMessageToOpponent(new String[] {checkHit, testPlayer.shoot()});
        System.out.println("testPlayer skjuter: " + pOneShot);


        for (int i = 0; i < 10; i++) {
            testPlayer2.getShot(testPlayer2.convertOpponentShot(pOneShot));
            String pTwoCheckHit = testPlayer2.checkHit(testPlayer2.convertOpponentShot(pOneShot));
            String pTwoShot = testPlayer2.buildMessageToOpponent(new String[] {pTwoCheckHit, testPlayer2.shoot()});
            System.out.println("testPlayer2 skjuter: " + pTwoShot);

            testPlayer.getShot(testPlayer.convertOpponentShot(pTwoShot));
            String pOneCheckH = testPlayer.checkHit(testPlayer.convertOpponentShot(pTwoShot));
            pOneShot = testPlayer.buildMessageToOpponent(new String[] {pOneCheckH, testPlayer.shoot()});
            System.out.println("testPlayer skjuter: " + pOneShot);
        }

        System.out.println("testPlayer2 skjuter på denna karta");
        testPlayer.printPlayerMap();

        System.out.println("testPlayer skjuter på denna karta");
        testPlayer2.printPlayerMap();


        /*
        testPlayer.getShot(testPlayer.convertOpponentShot("h shot 0j"));
        System.out.println(testPlayer.initialShot());
        testPlayer.printPlayerMap();
        //System.out.println(testPlayer.checkHit(testPlayer.convertOpponentShot("h shot 4a")));
        //System.out.println(testPlayer.buildMessageToOpponent(new String[]{testPlayer.checkIfWeHitOpponent("h shot 5g"), testPlayer.shoot()}));

         */


    }
}
