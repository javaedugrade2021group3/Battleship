package com.edugrade.battleship;

public class Main {

    /**
     * TODO
     *  1. Se till att det inte går att skjuta på samma koordinat två gånger.
     *      1. Se över möjligheten till att skjuta på en närliggande koordinat om träff.
     *  2. Skapa en klass Boats, för att kunna få fram båtobjekt.
     *      1. Båtarna ska ha "liv" som håller koll på om de blir förstörda.
     *  3. Server/Client (Klienten som börjar skjuta)
     *  4. Skotten är en sträng som kommer se ut på detta vis:
     *      1. Första skottet: i shot b6
     *      2. Andra skottet meddelar om första är en träff eller miss och skriver: m shot c4
     *      3. Trejde skottet osv. h shot g4 där m = miss och h = hit
     *  5. Win condition
     * */

    public static void main(String[] args) {
        Menu.mainMenu();
    }
}
