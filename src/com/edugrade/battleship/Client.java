package com.edugrade.battleship;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Player attackingPlayer = new Player();

    public static void startClient() {

        final int WAIT_FOR_SECONDS = 2;
        final int PORT = 65001;
        final String IP = "127.0.0.1";

        attackingPlayer.drawPlayerMap();
        attackingPlayer.createFleet();
        attackingPlayer.placeFleetOnMap(attackingPlayer.getFleetLocation());
        attackingPlayer.generateShots();
        attackingPlayer.fillShotsArray();

        try {
            Socket socket = new Socket(IP, PORT);

            DataInputStream fromServer = new DataInputStream(socket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());

            String initShot = attackingPlayer.initialShot();
            attackingPlayer.addHitToArray(initShot);
            String replyFromServer = "";
            toServer.writeUTF(initShot);
            String endMessage = "game over";
            int boatSunk = 0;


            while (boatSunk < 10){
                System.out.println("Client waiting for " + WAIT_FOR_SECONDS + " seconds.");
                wait(WAIT_FOR_SECONDS);
                replyFromServer = fromServer.readUTF();
                System.out.println("From Server: " + replyFromServer);

                if (replyFromServer.equals(endMessage)) {
                    break;
                }

                String coordinates = attackingPlayer.getIncomingCoordinate(replyFromServer);
                boolean checkIfShipIsHit = attackingPlayer.checkShipPosition(coordinates);

                String returnLetter;
                String returnMessage;

                if (checkIfShipIsHit) {
                    int shipID = attackingPlayer.getShipID(coordinates);
                    attackingPlayer.decrementShipLife(shipID);
                    returnLetter = attackingPlayer.shipIsActive(shipID);
                    int[] markMap = attackingPlayer.convertOpponentShot(coordinates);
                    attackingPlayer.getShot(markMap);
                } else {
                    returnLetter = attackingPlayer.getHitValue(false);
                    int[] markMap = attackingPlayer.convertOpponentShot(coordinates);
                    attackingPlayer.getShot(markMap);
                }

                if (replyFromServer.substring(0, 1).equals("h")) {
                    coordinates = attackingPlayer.getIncomingCoordinate(attackingPlayer.getHitFromArray());
                    returnMessage = attackingPlayer.returnString(returnLetter, attackingPlayer.shootCloseToLastHit(coordinates));
                } else if (replyFromServer.substring(0, 1).equals("s")) {
                    boatSunk++;
                    attackingPlayer.clearCloseHitArray();
                    returnMessage = attackingPlayer.returnString(returnLetter, attackingPlayer.generateShot());
                } else {
                    returnMessage = attackingPlayer.returnString(returnLetter, attackingPlayer.generateShot());
                }

                toServer.writeUTF(returnMessage);
                System.out.println("Båtar Client har sänkt: " + boatSunk);
                System.out.println("From Client: " + returnMessage);
                attackingPlayer.printPlayerMap();
                attackingPlayer.addHitToArray(returnMessage);
                toServer.flush();
            }
            toServer.writeUTF(endMessage);
            toServer.flush();
            toServer.close();
            socket.close();

        }  catch (IOException e) {}
    }

    /**
     * This method is used to introduce a delay in the game.
     * @author Joachim Forsberg
     * @param seconds Delaytime in seconds
     * */
    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
