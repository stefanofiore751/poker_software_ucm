package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class Game2 extends Game {

    private final LinkedList<Carta> hand;

    private final LinkedList<Carta> table;

    public Game2() {
        jugada = new Jugada();
        hand = new LinkedList<>();
        table = new LinkedList<>();
    }

    void readInput() {
        // InputStream in =
        // Main.class.getClassLoader().getResourceAsStream("entrada.txt");
        try {
            // Open input file
            FileInputStream in = new FileInputStream("entrada2.txt");
            char value;
            char suit;

            // Read file entrada2.txt line by line, using readline, not reading the entire txt file and separate by the character ";" into an array of strings
            String[] line = new String(in.readAllBytes()).split(";");
    }

}
