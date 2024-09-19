package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

public class Main {

    private LinkedList<Carta> mano;

    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private int[] suits_cont,value_count;

    // Array number of cards of each value:
    //

    public Main() {
        mano = new LinkedList<>();
        suits_cont = new int[4];
        value_count = new int[13];
    }

    public static void main(String[] args) {

        Main mainObj = new Main();
        // Apartado
                mainObj.readInput();
                mainObj.CountSuits_Value();
                mainObj.writeoutput();
    }

    private void readInput() {
        FileInputStream in = null;

        try {
            // Abrir fichero input
            in = new FileInputStream("C:\\Users\\stefi\\Desktop\\poker\\src\\ludopata\\sl\\entrada.txt");

            char value;
            char suit;

            // Leer fichero
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();
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

    private void writeoutput() {
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\stefi\\Desktop\\poker\\src\\ludopata\\sl\\output.txt");
            // Scrivere l'output su file
            String result = "Conteggio semi: [Cuori: " + suits_cont[0] + ", Quadri: " + suits_cont[1]
                    + ", Fiori: " + suits_cont[2] + ", Picche: " + suits_cont[3] + "]\n" + "colore: " + Color() + "\nscala: " + Straight() + "\nscala colore: " + straightFlush();
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CountSuits_Value() {

        for (Carta c : mano) {
            switch (c.Getsuit()){
                case 'h': suits_cont[0]++; break;
                case 'd': suits_cont[1]++; break;
                case 'c': suits_cont[2]++; break;
                case 's': suits_cont[3]++; break;
            }
            switch (c.Getvalue()){
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
        }
    }

    private  boolean straightFlush(){
        return Color() && Straight();
    }

    private  boolean Color(){
        return suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5 ;
    }

    private  boolean Straight(){
    for(int i = 0; i < value_count.length;i++){
        if(value_count[i] == 1) return StraightHelper(i);
    }
        return false;
    }

    private boolean StraightHelper(int i){
        if(i > 0 && i < 9)
        return value_count[i + 1] == 1 && value_count[i + 2] == 1 && value_count[i + 3] == 1 && value_count[i + 4] == 1;
        else if(i == 0)
            return (value_count[i + 1] == 1 && value_count[i + 2] == 1 && value_count[i + 3] == 1 && value_count[i + 4] == 1) ||  value_count[9] == 1 && value_count[10] == 1 && value_count[11] == 1 && value_count[12] == 1;
        return false;
    }

    public boolean poker(){
        for(int i = 0; i < value_count.length;i++){
            if(value_count[i] == 4) return StraightHelper(i);
        }
        return false;
    }


}
