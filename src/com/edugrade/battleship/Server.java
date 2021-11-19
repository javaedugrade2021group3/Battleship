package com.edugrade.battleship;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    private static Player defendingPlayer = new Player();

    public static void startServer() {

        final int WAIT_FOR_SECONDS = 2;
        final int PORT = 65001;

        defendingPlayer.drawPlayerMap();
        defendingPlayer.createFleet();
        defendingPlayer.placeFleetOnMap(defendingPlayer.getFleetLocation());
        defendingPlayer.generateShots();
        defendingPlayer.fillShotsArray();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

            int boatSunk = 0;
            String messageFromClient = "";
            String endMessage = "game over";


            while (boatSunk < 10) {
                System.out.println("Server waiting for " + WAIT_FOR_SECONDS + " seconds.");
                wait(WAIT_FOR_SECONDS);

                messageFromClient = fromClient.readUTF();
                System.out.println("From Client: " + messageFromClient);

                if (messageFromClient.equals(endMessage)) {
                    break;
                }

                String coordinates = defendingPlayer.getIncomingCoordinate(messageFromClient);
                boolean checkIfShipIsHit = defendingPlayer.checkShipPosition(coordinates);

                String returnLetter;
                String returnMessage;

                if (checkIfShipIsHit) {
                    int shipID = defendingPlayer.getShipID(coordinates);
                    defendingPlayer.decrementShipLife(shipID);
                    returnLetter = defendingPlayer.shipIsActive(shipID);
                    int[] markMap = defendingPlayer.convertOpponentShot(coordinates);
                    defendingPlayer.getShot(markMap);
                } else {
                    returnLetter = defendingPlayer.getHitValue(false);
                    int[] markMap = defendingPlayer.convertOpponentShot(coordinates);
                    defendingPlayer.getShot(markMap);
                }

                if (messageFromClient.substring(0,1).equals("h")) {
                    coordinates = defendingPlayer.getIncomingCoordinate(defendingPlayer.getHitFromArray());
                    returnMessage = defendingPlayer.returnString(returnLetter, defendingPlayer.shootCloseToLastHit(coordinates));
                } else if (messageFromClient.substring(0,1).equals("s")) {
                    boatSunk++;
                    defendingPlayer.clearCloseHitArray();
                    returnMessage = defendingPlayer.returnString(returnLetter, defendingPlayer.generateShot());
                } else {
                    returnMessage = defendingPlayer.returnString(returnLetter, defendingPlayer.generateShot());
                }

                toClient.writeUTF(returnMessage);
                System.out.println("Båtar Server har sänkt: " + boatSunk);
                System.out.println("From server: " + returnMessage);
                defendingPlayer.printPlayerMap();
                defendingPlayer.addHitToArray(returnMessage);
                defendingPlayer.printShotQueue();
                toClient.flush();
            }

            toClient.writeUTF(endMessage);
            toClient.flush();
            fromClient.close();
            socket.close();

        } catch (IOException e) {}
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

