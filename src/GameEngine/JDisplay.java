package GameEngine;

import Chip8.Display;
public class JDisplay implements Display {
	static int SCREEN_SIZE = 256;
	byte [] m_screen;
	public JDisplay(){
		m_screen = new byte[SCREEN_SIZE];
		
	}
	public byte getScreen(int index)
	{	
		return m_screen[index];
	}
	public void setScreen(int index, byte value)
	{
		m_screen[index] = value;
	}
	public void print()
	{//TODO notify to display
		System.out.println("JDisplay print");
	}
	public void clear(){
		for (int i = 0; i< SCREEN_SIZE; i++){
			m_screen[i] = 0;
		}
		print();
	}

}
