package GameEngine;

import Chip8.IKeyBoard;


public class KeyBoard implements IKeyBoard {
	private byte m_value;
	private char m_key;
	public KeyBoard(){
		m_value = (byte)0xff;
		m_key = 0xff;
	}
	//this function need implements 
	/*Fx0A - LD Vx, K
    Wait for a key press, store the value of the key in Vx.

    All execution stops until a key is pressed, then the value of that key is stored in Vx.*/
	public byte waitForValue(){
		byte value = (byte)0xff;
		
		while(true){
			System.out.println("please input key");
			while(m_key == 0xff);//wait key pressed
			value = convertCharToValue(m_key);
			if (m_value != 0xff){
				break;
			}
		}
		setKey((char)0xff);
		m_value = value;
		return m_value;
	}
	
	public synchronized void setKey(char value){
		m_key = value;
	}
	
	public void keyPressed(char value){
		setKey(value);
	}
	
	private byte convertCharToValue(char key){
		if(key >= '0' && key <= '9')
			return (byte)(((int)key) - 48);
		if(key >= 'a' && key <= 'f')
			return (byte)(((int)key) - 87);
		return (byte)0xff;
	}
	
	public void setValue(byte value){
		m_value = value;
	}
	public boolean getValue(byte key){
		return m_value == key;
	}
}

