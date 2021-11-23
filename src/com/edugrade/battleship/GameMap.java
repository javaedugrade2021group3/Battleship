package com.edugrade.battleship;

import java.util.Arrays;

/**
 * GameMap for the battleship game.
 * This class creates a multidimensional array which represents the map.
 * This GameMap class should be instantiated in the Player class
 * to create a map that is bound to the player object.
 *
 * @author Joachim Forsberg
 * @version 1.0
 * @since 2021-11-01
 * */

public class GameMap {

    private String[][] map = new String[11][11];

    public GameMap() {}

    /**
     * This method returns the String value that is stored in
     * the map at a particular index.
     * @param y The y-axis value
     * @param x The x-axis value
     * */
    public String getIndexValue(int y, int x) {
        return map[y][x];
    }

    /**
     * This method allows to assign a particular String value to the
     * index of the map.
     * @param value This is the string value that is going to be assigned to the map
     * @param y The y-axis value
     * @param x The x-axis value
     * */
    public String setIndexValue(String value, int y, int x) {
        return this.map[y][x] = value;
    }

    /**
     * This method generates the map.
     * */
    public void drawMap() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                map[i][j] = " ";
                map[0][i] = i - 1 + "";
                map[1][0] = "A]";
                map[2][0] = "B]";
                map[3][0] = "C]";
                map[4][0] = "D]";
                map[5][0] = "E]";
                map[6][0] = "F]";
                map[7][0] = "G]";
                map[8][0] = "H]";
                map[9][0] = "I]";
                map[10][0] = "J]";
                map[0][0] = "-]";
            }
        }
    }

    /**
     * This method prints the map to the terminal.
     * */
    public void printMap() {
        Arrays.asList(map).forEach(e -> System.out.println(Arrays.deepToString(e)));
    }
}
