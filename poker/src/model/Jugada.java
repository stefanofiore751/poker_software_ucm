package logic;

import java.util.HashMap;
import java.util.Map;

public class Jugada {

    private Map<Jugadas, Boolean> map;

    public Jugada() {
        map = new HashMap<>();

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

    public boolean getValue(Jugadas j) {
        return map.get(j);
    }

    public void updateMap(Jugadas j, boolean b) {
        map.put(j, b);
    }

    public enum Jugadas {
        STRAIGHT_FLUSH, POKER, FULL_HOUSE, STRAIGHT, FLUSH, TRIO, PAIR, HIGH_CARD, GUTSHOT, OPEN_ENDED, FLUSH_DRAW
    }
}
