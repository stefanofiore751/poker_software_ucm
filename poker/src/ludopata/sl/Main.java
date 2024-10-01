package ludopata.sl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Apartado
    	/*if(args.length != 6) {
    		System.out.println("Incorrect arguments");
    	}
    	
        int apartado = Integer.parseInt(args[3]);*/
        int apartado = 2;

        switch (apartado) {
            case 1:
                Game mainObj = new Game();
                mainObj.readInput();
                mainObj.writeoutput();
                break;
            case 2:
                game2Start();
                break;
        }


    }
    private static void game2Start() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mainObj.measurePerformance(1000);
    }


}
