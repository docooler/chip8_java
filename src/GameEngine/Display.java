package GameEngine;

import Chip8.IDisplay;
public class Display implements IDisplay {
	static int SCREEN_SIZE = 256;
	static int SCREEN_HEIGH = 32;
	static int SCREEN_WITH  = 8;
	
	byte [] m_screen;
	String []m_content;
	public Display(){
		m_screen = new byte[SCREEN_SIZE];
		m_content = new String[32];
	}
	public byte getScreen(int index)
	{	
		return m_screen[index];
	}
	public void setScreen(int index, byte value)
	{
		System.out.printf("setScreen index : %d, value: %d\n", index, value);
		m_screen[index] = value;
	}
	public void print()
	{
		for(int y = 0; y < SCREEN_HEIGH; y++){
			m_content[y] = ""; 
			for(int x = 0; x < SCREEN_WITH; x++){
				int screenIndex = y*SCREEN_WITH + x;
				byte value = m_screen[screenIndex];
				byte flag = 1;
				for(int i = 0 ; i < 8; i ++){//every byte is 8 bit
					flag = (byte)(1 << i);
					if((flag&value)>0){
						m_content[y] += "*";
					}else{
						m_content[y] += " ";
					}
				}
				
			}
		}
	}
	public void clear(){
		for (int i = 0; i< SCREEN_SIZE; i++){
			m_screen[i] = 0;
		}
		print();
	}

}
