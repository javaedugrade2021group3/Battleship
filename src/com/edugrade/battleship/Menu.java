package com.edugrade.battleship;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static Player player = new Player();
    private static Computer computer = new Computer();
    private static Server server = new Server();
    private static Client client = new Client();

    public static void mainMenu() throws IOException {
        ShowStartProgramOptions();
    }
    public static void ShowStartProgramOptions() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("Välkomen till spelet! nu kör vi");
        System.out.println("\n" +"\n" +
                "\n" +
                "\n" +
                "                                     # #  ( )\n" +
                "                                  ___#_#___|__\n" +
                "                              _  |____________|  _\n" +
                "                       _=====| | |            | | |==== _\n" +
                "                 =====| |.---------------------------. | |====\n" +
                "   <--------------------'   .  .  .  .  .  .  .  .   '--------------/\n" +
                "     \\                                                             /\n" +
                "      \\_______________________________________________WWS_________/\n" +
                "  wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "   wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww \n"
        );
        int startChoice;
        String startOptions = "1: Starta spelet som server\n" +
                "2: Starta spelet som client\n"+
                "0: Avsluta";

        System.out.println(startOptions);
        startChoice = scanner.nextInt();
        switch (startChoice){
            case 1:
                System.out.println("Server startade");
                //server.start(); den ska anropa direkt till showMenuOptionsAfterServerOrClientStart()
                server.startServer();
                break;
            case 2:
                System.out.println("Client startade");
                //client.start();den ska anropa direkt till showMenuOptionsAfterServerOrClientStart()
                client.startClient();
            case 0:
                System.out.println("Avslutar programmet..");
                break;

        }

    }

}