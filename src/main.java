import GameEngine.*;
import Chip8.*;

public class main {
	public static void main(String[] args){
		Machine machine = new Machine();
		boolean result = machine.init();
		if (result == false){
			System.out.println("Error : init");
			return;
		}
		result = machine.loadGame("PONG");
		if (result == false){
			System.out.println("Error : loadGame");
			return;
		}
	    boolean debug = true;
		if(debug){
			machine.debug();
		}
		machine.run();
		System.out.println(result);
		
		System.out.println("done");
	}
}