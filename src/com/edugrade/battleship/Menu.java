package com.edugrade.battleship;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player = new Player();
    private static Computer computer = new Computer();

    public static void mainMenu() {

        player.drawPlayerMap();
        computer.drawComputerMap();

        System.out.println("Sänkaskepp demoversion");
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
                    player.placeBoats();
                    computer.placeBoats();
                    break;
                case 2:
                    System.out.println("Slaget har börjat!");
                    startGame();
                case 0:
                    System.out.println("Avslutar programmet..");
                    break;
            }
        } while (choice != 0);
    }

    public static void startGame() {
        int rounds = 1;
        while (true) {
            // Spelaren börjar skjuta
            System.out.println("Omgång: " + rounds);
            int[] playerShots = player.shoot();
            computer.getShot(playerShots);
            System.out.println("Datorns spelplan");
            computer.printComputerMap();
            computer.checkHit(playerShots);

            // Datorn skjuter tillbaka
            int[] computerShots = computer.shoot();
            player.getShot(computerShots);
            System.out.println("Spelarens spelplan");
            player.printPlayerMap();
            player.checkHit(computerShots);
            rounds++;
        }
    }
}
