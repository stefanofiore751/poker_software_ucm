package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.Game;

public class Controller {
	
	private Game _game;
	
	private Hand _hand;
	
	private LinkedList<Hand> _hands;
	
	private int _numHands = 0;
	
	private FileReader _input;
	
	private FileWriter _output;
	
	public Controller(FileReader inputFile, FileWriter outputFile) throws Exception {
		_game = new Game();
		_hands = new LinkedList<>();
		_input = new FileReader(inputFile);
		_output = new FileWriter(outputFile);

	}
	//BufferedReader reader = new BufferedReader(inputFile);
	//BufferedWriter writer = new BufferedWriter(outputFile);
	
	// - CASE I -
	// Method which reads from the input file in the first case
	public void readC1() {
		try {
            // Open input file
            FileInputStream in = new FileInputStream(inputFile);
            //FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada.txt");
            
            char value;
            char suit;

            // Read file
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();
                _hand.addCard(new Card(value, suit));
                _game.countSuits_Value(value, suit);
            }
            _game.updateHand(_hand);
            // Close file
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// Method which writes from the output file in the first case
	public void writeC1() {
		_game.checkStrongestJugada();
        try {
            FileOutputStream out = new FileOutputStream(outputFile,true);
            // Scrivere l'output su file
            String result = _game.getMano() + "\n-Best Hand: " + _game.getStrongestJugada() + "\n";
            if (_game.getJugada().getValue(Plays.GUTSHOT))
                result += "-DRAW: Straight Gutshot\n";
            if (_game.getJugada().getValue(Plays.FLUSH_DRAW))
                result += "-DRAW: flush\n";
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// - CASE II -
	// Method which reads from the input file in the second case
	public void readC2() {
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
	
	// Method which writes from the output file in the second case
	public void writeC2() {
		
	}
	
	// - CASE III -
	// Method which reads from the input file in the third case
	public void readC3() {
			
	}
	
	// Method which writes from the output file in the third case
	public void writeC3() {
		
	}
}
