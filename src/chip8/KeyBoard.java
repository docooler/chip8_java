package chip8;

public class KeyBoard implements IKeyBoard {
	private byte m_value;
	public KeyBoard(){
		m_value = (byte)0xff;
	}
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
