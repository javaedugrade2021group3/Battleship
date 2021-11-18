package com.edugrade.battleship;

import java.util.Scanner;

public class StartOptions {
    Scanner scanner = new Scanner(System.in);
    private static Player player = new Player();
    private static Computer computer = new Computer();

    public void ShowStartProgramOptions(){
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
                System.out.println("Server startar ...");
                //server.start(); den ska anropa direkt till showMenuOptionsAfterServerOrClientStart()
                showMenuOptionsAfterServerOrClientStart();
                break;
            case 2:
                System.out.println("Client startar ...");
                //client.start();den ska anropa direkt till showMenuOptionsAfterServerOrClientStart()
                showMenuOptionsAfterServerOrClientStart();
            case 0:
                System.out.println("Avslutar programmet..");
                break;

        }

    }
    public void showMenuOptionsAfterServerOrClientStart(){
        player.drawPlayerMap();
        computer.drawComputerMap();
        int choice;
        String menu = "1: Placera båtarna\n" +
                "2: Starta spelet\n" +
                "0: Avsluta";

        System.out.println(menu);

        do  {
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Båtarna placerade!");
                    //player.placeBoats();
                    //computer.placeBoats();
                    break;
                case 2:
                    System.out.println("Slaget har börjat!");
                    //startGame(); startGame()  give now en endless loop
                    break;
                case 0:
                    System.out.println("Avslutar spelet..");
                    break;
            }
        } while (choice != 0);

    }
    public static void startGame() {
        int rounds = 1;
        while (true) {
            // Spelaren börjar skjuta
            System.out.println("Omgång: " + rounds);
            //String playerShots = player.shoot();
            //computer.getShot(playerShots);
            System.out.println("Datorns spelplan");
            computer.printComputerMap();
            //computer.checkHit(playerShots);

            // Datorn skjuter tillbaka
            int[] computerShots = computer.shoot();
            player.getShot(computerShots);
            System.out.println("Spelarens spelplan");
            player.printPlayerMap();
            //player.checkHit(computerShots);
            rounds++;
        }
    }
}
