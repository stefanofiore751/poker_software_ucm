package ludopata.sl;

public class Main {

    public static void main(String[] args) {

        // Apartado
    	if(args.length < 6)
        int apartado = Integer.parseInt(args[3]);
        
        switch(apartado) {
        case 1:
        	Game mainObj = new Game();
        	mainObj.readInput();
            mainObj.writeoutput();
            break;
        case 2:
        	Game2 mainObj2 = new Game2();
        	mainObj2.readInput();
            mainObj2.writeoutput();
            break;
        }
        
        //mainObj.measurePerformance(1000);
    }



}
