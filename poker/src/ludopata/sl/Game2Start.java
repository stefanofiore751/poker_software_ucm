package ludopata.sl;

import java.io.*;

public class Game2Start {

    public Game2Start() throws IOException {
        File file = new File("..\\poker_software_ucm\\poker\\resources\\output2.txt");
        file.delete();
        String percorsoFile = "..\\\\poker_software_ucm\\\\poker\\\\resources\\\\entrada2.txt";  // Sostituisci con il percorso reale del file

        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                Game2 game = new Game2();
                game.readInput(linea);
                game.writeoutput();
            }
        }
        catch(IOException e) {
        e.printStackTrace();
    }

}
    }

