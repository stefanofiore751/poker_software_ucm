package controller;

import logic.Game;
import logic.Game2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static Controller ctrl;

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Incorrect arguments. Usage: java -jar nombreProyecto.jar <caseNumber> <inputFile> <outputFile>");
            return;
        }

        // Legge gli argomenti
        int caseNumber = Integer.parseInt(args[0]); // Numero del caso
        String inputFile = args[1]; // File di input
        String outputFile = args[2]; // File di output

        switch (caseNumber) {
            case 1:
                game1Start(inputFile, outputFile);
                break;
            case 2:
                game2Start(inputFile, outputFile);
                break;
            case 3:
                game3Start(inputFile, outputFile);
                break;
            default:
                System.out.println("Invalid case number. Use 1, 2, or 3.");
                break;
        }


    }
    private static void game1Start(String inputFile, String outputFile) {
        Game mainObj = new Game();
        mainObj.readInput(inputFile);  // Legge dal file di input
        mainObj.writeoutput(outputFile);  // Scrive nel file di output
    }

    // Metodo per il caso 2
    private static void game2Start(String inputFile, String outputFile) {
        // Elimina il file di output esistente, se c'è
        File file = new File(outputFile);
        file.delete();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Game2 game = new Game2();
                game.readInput(linea);
                game.writeoutput(outputFile);  // Scrive nel file di output
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per il caso 3 (aggiungere la logica se necessario)
    private static void game3Start(String inputFile, String outputFile) {
        // Aggiungi la logica per il caso 3 qui
        System.out.println("Game 3 logic goes here.");
    }
}
