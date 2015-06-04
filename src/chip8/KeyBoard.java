package Chip8;

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
		byte prveValue = m_value;
		m_value = (byte)0xff;
		return prveValue;
	}
	
	public void setValue(byte value){
		m_value = value;
	}
	public boolean getValue(byte key){
		return m_value == key;
	}
}
