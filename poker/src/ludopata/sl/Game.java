package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import
public class Game {
    private final LinkedList<Carta> mano;

    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private final int[] suits_cont;
    private final int[] value_count;
    private Jugada jugada;


    public Game() {
        mano = new LinkedList<>();
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Jugada();
    }




    void readInput() {
        FileInputStream in = null;

        try {
            // Abrir fichero input
            in = new FileInputStream("C:\\Users\\stefi\\Documents\\GitHub\\poker_software_ucm\\poker\\src\\ludopata\\sl\\entrada.txt");

            char value;
            char suit;

            // Leer fichero
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();
                switch (suit){
                    case 'h': suits_cont[0]++; break;
                    case 'd': suits_cont[1]++; break;
                    case 'c': suits_cont[2]++; break;
                    case 's': suits_cont[3]++; break;
                }
                switch (value){
                    case 'A': value_count[0]++; break;
                    case '2': value_count[1]++; break;
                    case '3': value_count[2]++; break;
                    case '4': value_count[3]++; break;
                    case '5': value_count[4]++; break;
                    case '6': value_count[5]++; break;
                    case '7': value_count[6]++; break;
                    case '8': value_count[7]++; break;
                    case '9': value_count[8]++; break;
                    case 'T': value_count[9]++; break;
                    case 'J': value_count[10]++; break;
                    case 'Q': value_count[11]++; break;
                    case 'K': value_count[12]++; break;
                }
                mano.add(new Carta(value, suit));
            }

            // Cerrar fichero
            in.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void writeoutput() {
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\stefi\\Documents\\GitHub\\poker_software_ucm\\poker\\src\\ludopata\\sl\\output.txt");
            // Scrivere l'output su file
            String result = "Conteggio semi: [Cuori: " + suits_cont[0] + ", Quadri: " + suits_cont[1]
                    + ", Fiori: " + suits_cont[2] + ", Picche: " + suits_cont[3] + "]\n" + "colore: " + Color() + "\nscala: " + Straight() + "\nscala colore: " + straightFlush();
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void straightFlush(){
        Color();
        Straight();
        if(jugada.getValue(Jugada.Jugadas.STRAIGHT_FLUSH) && jugada.getValue(Jugada.Jugadas.FLUSH)) jugada.updateMap(Jugada.Jugadas.STRAIGHT_FLUSH,true);
    }

    private  void Color(){
        if(suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5) jugada.updateMap(Jugada.Jugadas.FLUSH,true); ;
    }

    private void Straight(){
        boolean twoFound = false;
        int consecutive = 0,gaps = 0;
        for(int i = 0; i < value_count.length; i++){
            if(value_count[i] == 2){
                if(!twoFound)
                    twoFound = true;
                else return;
            }
            else if(value_count[i] > 2) return;
            else if(value_count[i] == 1){
                consecutive++;
                if(consecutive == 5){
                    jugada.updateMap(Jugada.Jugadas.STRAIGHT,true);
                    return;
                }
            } if(consecutive != 0 && consecutive <4) gaps++;
            if(gaps == 2) return;
        }
        if(consecutive == 4 && gaps == 1)
            jugada.updateMap(Jugada.Jugadas.OPEN_ENDED,true);
        else if(consecutive == 4 && gaps == 0) jugada.updateMap(Jugada.Jugadas.GUTSHOT,true);
    }

    public boolean poker(){
        for (int j : value_count) {
            if (j == 4) return true;
        }
        return false;
    }


}
