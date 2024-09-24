package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
public class Game {
    private final LinkedList<Carta> mano;

    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private final int[] suits_cont;
    int[] value_count;
    Jugada jugada;


    public Game() {
        mano = new LinkedList<>();
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Jugada();
    }




    void readInput() {
        //InputStream in = Main.class.getClassLoader().getResourceAsStream("entrada.txt");
        try {
            // Abrir fichero input
            FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada.txt");
            char value;
            char suit;

            // Leer fichero
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();
                System.out.println(value);
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
            FileOutputStream out = new FileOutputStream("..\\poker_software_ucm\\poker\\resources\\output.txt");
            // Scrivere l'output su file
            String result = "Conteggio semi: [Cuori: " + suits_cont[0] ;
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // I) Straight Flush
    private  void straightFlush(){
        Color();
        Straight();
        if(jugada.getValue(Jugada.Jugadas.STRAIGHT_FLUSH) && jugada.getValue(Jugada.Jugadas.FLUSH)) jugada.updateMap(Jugada.Jugadas.STRAIGHT_FLUSH,true);
    }
    // II) Poker (four-of-a-kind)
    public void poker(){
        for (int j : value_count) {
            if (j == 4){
                jugada.updateMap(Jugada.Jugadas.POKER,true);
                return;
            }
        }
    }

    // III) Full House (El Barco)
    private void full() {
         trio();
         pair();
         if(jugada.getValue(Jugada.Jugadas.TRIO) && jugada.getValue(Jugada.Jugadas.PAIR)) jugada.updateMap(Jugada.Jugadas.FULL_HOUSE,true);
    }

    // IV) Flush (color)
    private  void Color(){
        if(suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5) jugada.updateMap(Jugada.Jugadas.FLUSH,true);
        if(suits_cont[0] == 4 || suits_cont[1] == 4 || suits_cont[2] == 4 || suits_cont[3] == 4) jugada.updateMap(Jugada.Jugadas.FLUSH_DRAW,true);

    }

    // V) Straight (escalera)
    void Straight(){
        int[] new_value_count = new int[value_count.length + 1];
        System.arraycopy(value_count, 0, new_value_count, 0, value_count.length);
        new_value_count[new_value_count.length - 1] = value_count[0];

        boolean twoFound = false;
        int consecutive = 0,gaps = 0;
        for (int j : new_value_count) {
            if (j == 2) {
                if (!twoFound)
                    twoFound = true;
                else return;
            } else if (j > 2) return;
            if (j == 1 || j == 2) {
                consecutive++;
                if (consecutive == 5 && gaps == 0) {
                    jugada.updateMap(Jugada.Jugadas.STRAIGHT, true);
                    jugada.updateMap(Jugada.Jugadas.OPEN_ENDED, false);
                    return;
                }
                if (consecutive == 4 && gaps == 1) {
                    jugada.updateMap(Jugada.Jugadas.GUTSHOT, true);
                    return;
                } else if (consecutive == 4) {
                    jugada.updateMap(Jugada.Jugadas.OPEN_ENDED, true);
                }

            } else if (consecutive != 0) gaps++;
            if (gaps == 2) {
                consecutive = 0;
                gaps = 0;
            }
        }
        }

    // VI) Three-of-a-kind (trio)
    private void trio() {
        for (int j : value_count) {
            if (j == 3){
               jugada.updateMap(Jugada.Jugadas.TRIO, true);
               return;
            }
        }
    }

    // VII) Two-pair (pareja)
    private void pair() {
        for (int j : value_count) {
            if (j == 2){
                jugada.updateMap(Jugada.Jugadas.PAIR, true);
                return;
            }
        }
    }



}
