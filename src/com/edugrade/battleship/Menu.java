package com.edugrade.battleship;

import java.util.Scanner;

public class Menu {

    public static void mainMenu() {
        ShowStartProgramOptions();
    }
    public static void ShowStartProgramOptions() {
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
                Server.startServer();
                break;
            case 2:
                System.out.println("Client startade");
                //Client.startClient(); // Denna är bortkommenterad för att det ligger en main i client för test
            case 0:
                System.out.println("Avslutar programmet..");
                break;
        }
    }
}