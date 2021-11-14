package com.edugrade.battleship;


import java.util.Random;

public class Main {

    private static Player testPlayer = new Player(); // Test objekt

    public static void main(String[] args) {
        Menu.mainMenu();

        /**
         * Testar Båtobjekten.
         * */
        testPlayer.drawPlayerMap(); // Genererar spelplanen
        testPlayer.createFleet(); // Skapar spelarens flotta
        testPlayer.placeFleetOnMap(testPlayer.getFleetLocation()); // Placerar flottan på spelplanen
        //testPlayer.printPlayerMap(); // Skriver ut spelplanen i terminalen


        testPlayer.getShot(testPlayer.convertOpponentShot("h shot 0j"));
        System.out.println(testPlayer.initialShot());
        testPlayer.printPlayerMap();
        //System.out.println(testPlayer.checkHit(testPlayer.convertOpponentShot("h shot 4a")));
        //System.out.println(testPlayer.buildMessageToOpponent(new String[]{testPlayer.checkIfWeHitOpponent("h shot 5g"), testPlayer.shoot()}));


    }
}
