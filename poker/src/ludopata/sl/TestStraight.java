package ludopata.sl;

import java.util.Arrays;

public class TestStraight { //just to test the straight method - can be removed

    public static void main(String[] args) {
        //testStraightHand();
        //testGutshotHand();
        testOpenEndedHand();
        testAceLowStraightHand();
        //testNoStraightHand();
    }

    // Metodo per testare una scala normale
    private static void testStraightHand() {
        System.out.println("Testing a straight hand:");
        Game game = new Game();
        // Impostiamo una mano con una scala 5-6-7-8-9 (cuori)
        game.value_count = new int[13]; // Reset value count
        game.value_count[4] = 1; // 5
        game.value_count[5] = 1; // 6
        game.value_count[6] = 1; // 7
        game.value_count[7] = 1; // 8
        game.value_count[8] = 1; // 9

        game.Straight(); // Testiamo la funzione
        System.out.println("Expected: true for Straight.");
        System.out.println("\nstraight: " + game.jugada.getValue(Jugada.Jugadas.STRAIGHT));
        System.out.println("\nOpenHand: " +game.jugada.getValue(Jugada.Jugadas.OPEN_ENDED));
        System.out.println("\nGutshot: " +game.jugada.getValue(Jugada.Jugadas.GUTSHOT));

    }

    // Metodo per testare un gutshot (es. 5-6-8-9-10)
    private static void testGutshotHand() {
        System.out.println("Testing a gutshot hand:");
        Game game = new Game();
        // Impostiamo una mano con un gutshot 5-6-8-9-10 (manca il 7)
        game.value_count = new int[13]; // Reset value count
        game.value_count[4] = 1; // 5
        game.value_count[5] = 1; // 6
        game.value_count[7] = 1; // 8
        game.value_count[8] = 2; // 9
        game.value_count[9] = 0; // 10

        game.Straight(); // Testiamo la funzione
        System.out.println("Expected: true for Gutshot.");
        System.out.println("\nstraight: " + game.jugada.getValue(Jugada.Jugadas.STRAIGHT));
        System.out.println("\nOpenHand: " +game.jugada.getValue(Jugada.Jugadas.OPEN_ENDED));
        System.out.println("\nGutshot: " +game.jugada.getValue(Jugada.Jugadas.GUTSHOT));

    }

    // Metodo per testare un draw open-ended (es. 5-6-7-8, manca il 4 o 9)
    private static void testOpenEndedHand() {
        System.out.println("Testing an open-ended hand:");
        Game game = new Game();
        // Impostiamo una mano con un open-ended draw 5-6-7-8 (manca 4 o 9)
        game.value_count = new int[13]; // Reset value count
        game.value_count[4] = 1; // 5
        game.value_count[5] = 1; // 6
        game.value_count[6] = 2; // 7
        game.value_count[7] = 1; // 8
        game.value_count[9] = 0; // T
        game.Straight(); // Testiamo la funzione
        System.out.println("Expected: true for Open-Ended.");
        System.out.println("\nstraight: " + game.jugada.getValue(Jugada.Jugadas.STRAIGHT));
        System.out.println("\nOpenHand: " +game.jugada.getValue(Jugada.Jugadas.OPEN_ENDED));
        System.out.println("\nGutshot: " +game.jugada.getValue(Jugada.Jugadas.GUTSHOT));

    }

    // Metodo per testare una scala con Asso basso (A-2-3-4-5)
    private static void testAceLowStraightHand() {
        System.out.println("Testing an Ace-High straight hand:");
        Game game = new Game();
        // Impostiamo una mano con una scala A-2-3-4-5
        game.value_count = new int[13]; // Reset value count
        game.value_count[0] = 1; // A
        game.value_count[9] = 1; // T
        game.value_count[10] = 1; // J
        game.value_count[11] = 1; // Q
        game.value_count[12] = 1; // K

        game.Straight(); // Testiamo la funzione
        System.out.println("Expected: true for Ace-Low Straight.");
        System.out.println("\nstraight: " + game.jugada.getValue(Jugada.Jugadas.STRAIGHT));
        System.out.println("\nOpenHand: " +game.jugada.getValue(Jugada.Jugadas.OPEN_ENDED));
        System.out.println("\nGutshot: " +game.jugada.getValue(Jugada.Jugadas.GUTSHOT));

    }

    // Metodo per testare una mano senza scala
    private static void testNoStraightHand() {
        System.out.println("Testing a hand with no straight:");
        Game game = new Game();
        // Impostiamo una mano che non ha una scala
        game.value_count = new int[13]; // Reset value count
        game.value_count[1] = 1; // 2
        game.value_count[4] = 1; // 5
        game.value_count[7] = 1; // 8
        game.value_count[10] = 1; // J
        game.value_count[12] = 1; // K

        game.Straight(); // Testiamo la funzione
        System.out.println("Expected: false for no Straight.");
        System.out.println("\nstraight: " + game.jugada.getValue(Jugada.Jugadas.STRAIGHT));
        System.out.println("\nOpenHand: " +game.jugada.getValue(Jugada.Jugadas.OPEN_ENDED));
        System.out.println("\nGutshot: " +game.jugada.getValue(Jugada.Jugadas.GUTSHOT));
    }
}
