package com.edugrade.battleship;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Player testPlayer = new Player(); // Test objekt
    private static Player testPlayer2 = new Player();

    public static void main(String[] args) throws IOException {
        Menu.mainMenu();
        // Försvarare "server"
        testPlayer.drawPlayerMap(); // Genererar spelplanen
        testPlayer.createFleet(); // Skapar spelarens flotta
        testPlayer.placeFleetOnMap(testPlayer.getFleetLocation()); // Placerar flottan på spelplanen
        testPlayer.generateShots();
        testPlayer.fillShotsArray();

        // Attackerare "klient"
        testPlayer2.drawPlayerMap();
        testPlayer2.createFleet();
        testPlayer2.placeFleetOnMap(testPlayer2.getFleetLocation());
        testPlayer2.generateShots();
        testPlayer2.fillShotsArray();


        // Init shot by "client"
        String initShot = testPlayer2.initialShot();
        System.out.println("Initial Shot: " + initShot);

        // Init shot is received by "server"
        String coordinates = testPlayer.getIncomingCoordinate(initShot);
        System.out.println("Coordinates from the init shot: " + coordinates);

        boolean checkIfShipGotHit = testPlayer.checkShipPosition(coordinates);
        System.out.println("Check if ship was hit: " + checkIfShipGotHit);

        String returnLetter;
        String returnMessage;

        if (checkIfShipGotHit) { // If ship was hit
            int shipID = testPlayer.getShipID(coordinates);
            System.out.println("Ship ID: " + shipID);
            testPlayer.decrementShipLife(shipID);
            returnLetter = testPlayer.shipIsActive(shipID);
            System.out.println("First letter of return message if ship was hit: " + returnLetter);
            int[] markMap = testPlayer.convertOpponentShot(coordinates);
            testPlayer.getShot(markMap);
        } else { // If ship wasn't hit
            returnLetter = testPlayer.getHitValue(false);
            System.out.println("First letter of return message if ship wasn't hit: " + returnLetter);
            int[] markMap = testPlayer.convertOpponentShot(coordinates);
            testPlayer.getShot(markMap);
        }

        // Shoot back
        if (initShot.substring(0,1).equals("h")) {
            coordinates = testPlayer.getIncomingCoordinate(testPlayer.getHitFromArray());
            returnMessage = testPlayer.returnString(returnLetter, testPlayer.shootCloseToLastHit(coordinates));
        } else {
            returnMessage = testPlayer.returnString(returnLetter, testPlayer.generateShot());
        }

        System.out.println("Return shot: " + returnMessage);

        testPlayer.printPlayerMap();
        testPlayer.addHitToArray(returnMessage);
    }
}
