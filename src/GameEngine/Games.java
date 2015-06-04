package GameEngine;

import java.io.File;

public class Games {
	String gamePath = "C:\\workspace\\java\\chip8_java\\c8games";
	String[] games;
	
	public Games(){
		
	}
	public void listAllGames(){
		File fd = new File(gamePath);
		
		if(fd.isDirectory()){
			games = fd.list();
		}
		for (int i=0; i< games.length; i++){
			System.out.println(games[i]);
		}
		
	}

}
