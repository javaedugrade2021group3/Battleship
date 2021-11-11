package com.edugrade.battleship;

public class Main {

    private static Player testPlayer = new Player(); // Test objekt

    public static void main(String[] args) {
        //Menu.mainMenu();

        /**
         * Testar Båtobjekten.
         * */
        testPlayer.drawPlayerMap(); // Genererar spelplanen
        testPlayer.createFleet(); // Skapar spelarens flotta
        testPlayer.placeFleetOnMap(testPlayer.getFleetLocation()); // Placerar flottan på spelplanen
        testPlayer.printPlayerMap(); // Skriver ut spelplanen i terminalen


    }
}
