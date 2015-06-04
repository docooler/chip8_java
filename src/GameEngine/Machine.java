package GameEngine;
import Chip8.*;
public class Machine {
	Memory m_memory;
	Cpu    m_cpu;
	IDisplay m_display;
	IKeyBoard m_keyboard;
	Load m_load; 
	
	public Machine(){
		m_memory = new Memory();
		m_display = new Display();
		m_keyboard = new KeyBoard();
		m_load    = new Load(); 
		
		m_cpu = new Cpu(m_memory, m_display, m_keyboard);
	}
	
	public boolean init(){
		boolean result = false;
		
		result = m_load.loadRomFromFile(m_memory);
		return result;
	}
	
	public boolean loadGame(String gameName){
		boolean result;
		result = m_load.loadGame(gameName, m_memory);
		if(result != true){
			System.out.println("load game error");
		}
		return result;
	}
	
	public void run(){
		int testCount = 0;
		boolean quit = false;
		while(true){
			testCount++;
			m_cpu.updateTimer();
			quit = m_cpu.clock();
			if(quit){
				System.out.println("System quit");
				break;
			}
//			if (testCount == 100){
//				System.out.println("run 100 times quit");
//				return;
//			}
		}
	}
	
	public void debug(){
		
		boolean quit = false;
		char inputKey;
		while(true){
			try{
			System.out.print(">");
			inputKey = (char)System.in.read();
			}catch(Exception e1){
				e1.printStackTrace();
			    return;	
			}
			switch (inputKey){
			case 'h':
				System.out.println("s - Step");
				System.out.println("p - Print next instruction");
				System.out.println("r - Print registers");
				System.out.println("d - Dump memory");
				System.out.println("q - quit debugger");
				System.out.println("h - help");
				break;
			case 's':
				m_cpu.updateTimer();
				quit = m_cpu.clock();
				if(quit){
					System.out.println("System quit");
					break;
				}
			case 'p':
				char pc = m_cpu.getPC();
				System.out.printf("0x%x%x", m_memory.getValue(pc++)&0xff, m_memory.getValue(pc)&0xff);
				break;
			case 'r':
				m_cpu.printRegister();
				break;
			case 'd':
				m_memory.printMemory();
			case 'q':
				System.exit(0);
			}
			
		}
	}

}
