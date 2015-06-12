import GameEngine.*;


public class main {
	public static void main(String[] args){
		Machine machine = new Machine();
		boolean result = machine.init();
		if (result == false){
			System.out.println("Error : init");
			return;
		}
		result = machine.loadGame("PONG2");
		if (result == false){
			System.out.println("Error : loadGame");
			return;
		}
	    boolean debug = false;
		if(debug){
			machine.debug();
		}
//		try {
//			Thread.sleep(50);
//		}catch(Exception e1){
//			
//		}
		
		machine.run();
		System.out.println(result);
		
		System.out.println("done");
	}
}