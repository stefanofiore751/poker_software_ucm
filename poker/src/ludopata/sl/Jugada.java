package ludopata.sl;

import java.util.HashMap;
import java.util.Map;

public class Jugada {
    //map to keep count of the hand we have
    private Map<Jugadas, Boolean> map = new HashMap<>();

    public Jugada() {
        map.put(Jugadas.STRAIGHT_FLUSH, false);
        map.put(Jugadas.POKER, false);
        map.put(Jugadas.FULL_HOUSE, false);
        map.put(Jugadas.STRAIGHT, false);
        map.put(Jugadas.FLUSH, false);
        map.put(Jugadas.TRIO, false);
        map.put(Jugadas.PAIR, false);
        map.put(Jugadas.HIGH_CARD, false);
        map.put(Jugadas.GUTSHOT, false);
        map.put(Jugadas.OPEN_ENDED, false);
        map.put(Jugadas.FLUSH_DRAW, false);
    }

    public void updateMap(Jugadas j, boolean b){
        map.put(j,b);
    }

    public boolean getValue(Jugadas j){
        return map.get(j);
    }
    public enum Jugadas
    {
        STRAIGHT_FLUSH, POKER, FULL_HOUSE, STRAIGHT,FLUSH, TRIO, PAIR, HIGH_CARD,GUTSHOT,OPEN_ENDED,FLUSH_DRAW
    }
}
