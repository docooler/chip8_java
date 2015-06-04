package GameEngine;

import Chip8.IKeyBoard;

public class KeyBoard implements IKeyBoard {
	private byte m_value;
	public KeyBoard(){
		m_value = (byte)0xff;
	}
	//this function need implements 
	/*Fx0A - LD Vx, K
    Wait for a key press, store the value of the key in Vx.

    All execution stops until a key is pressed, then the value of that key is stored in Vx.*/
	public byte waitForValue(){
		char inputKey = 0xff;
		byte value = (byte)0xff;
		
		while(inputKey == 0xff){
			try{
				inputKey = (char)System.in.read();
				}catch(Exception e1){
					e1.printStackTrace();
				    return (byte)0xff;	
				}
			
			value = convertCharToValue(inputKey);
		}
		m_value = value;
		return value;
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

