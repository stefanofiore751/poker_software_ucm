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
        try{
            // Open input file
            FileInputStream in = new FileInputStream("entrada2.txt");
            char value;
            char suit;

            BufferedReader reader = new BufferedReader(new FileReader(in));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

            //Read file entrada2
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
