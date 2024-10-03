package controller;

import logic.Game;
import logic.Game2;

import java.io.*;

public class Main {
	
	// (Alba) I changed the following files: Suits, Player, Hand, Controller, Game and Main
	// Im not done with the changes in order to have only one Game and do properly the MVC,
	// but I tried at least to fix some of it so you can see how we have to do it

    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            System.out.println("Incorrect arguments. Usage: java -jar PokerProject.jar <caseNumber> <inputFile> <outputFile>");
            return;
        }

        // Parse arguments
        int caseNumber = Integer.parseInt(args[0]);

        //String inputFile = args[1];
        //String outputFile = args[2];
		FileReader fileReader = new FileReader(args[1]); //args[1] = inputFile
		FileWriter fileWriter = new FileWriter(args[2]); //args[2] = outputFile

        Controller ctrl = new Controller(fileReader, fileWriter);
        
        // CASES
        switch (caseNumber) {
            case 1:
                //game1Start(inputFile, outputFile);
            	// -->
            	//mainObj.readInput(inputFile);  // read from input file
            	//ctrl.readC1();
            	//mainObj.writeoutput(outputFile);  // write on output file
            	//ctrl.writeC1();
                ctrl.setGame(1);
                ctrl.read();
                break;
            case 2:
            	//game2Start(inputFile, outputFile);
            	//ctrl.readC2();
            	//ctrl.writeC2();
                ctrl.setGame(2);
                ctrl.read();
                break;
            case 3:
                //game3Start(inputFile, outputFile);
            	//ctrl.readC3();
            	//ctrl.writeC3();
                ctrl.setGame(3);
                ctrl.read();
                break;
            default:
                System.out.println("Invalid case number. Use 1, 2, or 3.");
                break;
        }
    }
}
