package com.edugrade.battleship;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static StartOptions startOptions = new StartOptions();
    public static void mainMenu() {
        startOptions.ShowStartProgramOptions();
    }
}