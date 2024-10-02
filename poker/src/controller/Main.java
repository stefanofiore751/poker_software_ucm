package controller;

import logic.Game;
import logic.Game2;
import logic.Game3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static Controller ctrl;

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Incorrect arguments. Usage: java -jar PokerProject.jar <caseNumber> <inputFile> <outputFile>");
            return;
        }

        // read arguments
        int caseNumber = Integer.parseInt(args[0]); // case number
        String inputFile = args[1]; // input file
        String outputFile = args[2]; //output file

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
        mainObj.readInput(inputFile);  // read from input file
        mainObj.writeoutput(outputFile);  // write on output file
    }

    // Metodo per il caso 2
    private static void game2Start(String inputFile, String outputFile) {
        File file = new File(outputFile);
        file.delete();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Game2 game = new Game2();
                game.readInput(linea);
                game.writeoutput(outputFile);  // write on output file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO CASE 3
    private static void game3Start(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Game3 game = new Game3();
                game.readInput(line);
                game.writeoutput(outputFile);  // write on output file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
