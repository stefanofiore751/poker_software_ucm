import java.util.HashMap;
import java.util.Map;

public enum Plays {
    STRAIGHT_FLUSH, POKER, FULL_HOUSE, STRAIGHT, FLUSH, TRIO, PAIR,
    	HIGH_CARD, GUTSHOT, OPEN_ENDED, FLUSH_DRAW
}

public class Play {
	private Map<Plays, Boolean> map;

    public Play() {
        map = new HashMap<>();

        map.put(Plays.STRAIGHT_FLUSH, false);
        map.put(Plays.POKER, false);
        map.put(Plays.FULL_HOUSE, false);
        map.put(Plays.STRAIGHT, false);
        map.put(Plays.FLUSH, false);
        map.put(Plays.TRIO, false);
        map.put(Plays.PAIR, false);
        map.put(Plays.HIGH_CARD, false);
        map.put(Plays.GUTSHOT, false);
        map.put(Plays.OPEN_ENDED, false);
        map.put(Plays.FLUSH_DRAW, false);
    }
    
    public boolean getValue(Plays j) {
        return map.get(j);
    }

    public void updateMap(Plays j, boolean b) {
        map.put(j, b);
    }
}
