package chip8;

public class DelayTimer implements ITimer {
	private byte m_value;
	
	public DelayTimer(){
		
	}
	
	public byte get(){
		return m_value;
	}
	public void set(byte value){
		m_value = value;
	}
	
	void Tick(){
		if (m_value > 0){
			m_value--;
		}
	}

}
