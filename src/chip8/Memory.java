package Chip8;

public class Memory {
	
	byte [] m_memoryBuffer;
	int  LENGTH = 4096;
	int m_programLength;
	
	public Memory(){
		m_memoryBuffer = new byte[4096];
	}
	public int length(){
		return LENGTH;
	}
	
	public void setValue(char address, byte value){
		m_memoryBuffer[address] = value;
	}
	
	public byte getValue(char address){
		return m_memoryBuffer[address];
	}
	
	public void loadProgram(byte[] program){
		System.arraycopy(program, 0, m_memoryBuffer, 0x200, program.length);
		m_programLength = program.length;
	}
	
	public void printMemory(){
		for (int i = 0x200; i< 0x200 + m_programLength; ){
			System.out.printf("pc 0x%2x : 0x%2x%2x\n",i, m_memoryBuffer[i]&0xff,m_memoryBuffer[i+1]&0xff);
			i += 2;
		}
	}
	
	public void loadAt(byte[] program, int position ){
		if(position > LENGTH){
			System.out.printf("loadAt error position %d\n", position );
			return;
		}
		for (int i= position; i < position + program.length; i++){
			m_memoryBuffer[i] = program[i];
		}
	}
	
	public void initalizeRom(byte[] rom){
		for(int i = 0; i < rom.length; i++ ){
			m_memoryBuffer[i] = rom[i];
		}
	}
	
	public void initializeInterpreterBuffer(){
				// 0                                    
				m_memoryBuffer[0] = (byte)0xF0;     
				m_memoryBuffer[1] = (byte)0x10;     
				m_memoryBuffer[2] = (byte)0xF0;     
				m_memoryBuffer[3] = (byte)0x80;     
				m_memoryBuffer[4] = (byte)0xF0;     
				// 1                                
				m_memoryBuffer[5] = (byte)0x20;     
				m_memoryBuffer[6] = (byte)0x60;     
				m_memoryBuffer[7] = (byte)0x20;     
				m_memoryBuffer[8] = (byte)0x20;     
				m_memoryBuffer[9] = (byte)0x70;     
				// 2                                
				m_memoryBuffer[10] = (byte)0xF0;    
				m_memoryBuffer[11] = (byte)0x10;    
				m_memoryBuffer[12] = (byte)0xF0;    
				m_memoryBuffer[13] = (byte)0x80;    
				m_memoryBuffer[14] = (byte)0xF0;    
				//3 ;                               
				m_memoryBuffer[15] = (byte)0xF0;    
				m_memoryBuffer[16] = (byte)0x10;    
				m_memoryBuffer[17] = (byte)0xF0;    
				m_memoryBuffer[18] = (byte)0x10;    
				m_memoryBuffer[19] = (byte)0xF0;    
				//4 ;                               
				m_memoryBuffer[20] = (byte)0x90;    
				m_memoryBuffer[21] = (byte)0x90;    
				m_memoryBuffer[22] = (byte)0xF0;    
				m_memoryBuffer[23] = (byte)0x10;    
				m_memoryBuffer[24] = (byte)0x10;    
				//5 ;		2                           
				m_memoryBuffer[29] = (byte)0xF0;    
				m_memoryBuffer[25] = (byte)0xF0;    
				m_memoryBuffer[26] = (byte)0x80;    
				m_memoryBuffer[27] = (byte)0xF0;    
				m_memoryBuffer[28] = (byte)0x10;    
				//6 ;                               
				m_memoryBuffer[30] = (byte)0xF0;    
				m_memoryBuffer[31] = (byte)0x80;    
				m_memoryBuffer[32] = (byte)0xF0;    
				m_memoryBuffer[33] = (byte)0x90;    
				m_memoryBuffer[34] = (byte)0xF0;    
				// 7;		3                           
				m_memoryBuffer[35] = (byte)0xF0;    
				m_memoryBuffer[36] = (byte)0x10;    
				m_memoryBuffer[37] = (byte)0x20;    
				m_memoryBuffer[38] = (byte)0x40;    
				m_memoryBuffer[39] = (byte)0x40;    
				// 8;                               
				m_memoryBuffer[40] = (byte)0xF0;    
				m_memoryBuffer[41] = (byte)0x90;    
				m_memoryBuffer[42] = (byte)0xF0;    
				m_memoryBuffer[43] = (byte)0x90;    
				m_memoryBuffer[44] = (byte)0xF0;    
				// 9;		4                           
				m_memoryBuffer[45] = (byte)0xF0;    
				m_memoryBuffer[46] = (byte)0x90;    
				m_memoryBuffer[47] = (byte)0xF0;    
				m_memoryBuffer[48] = (byte)0x10;    
				m_memoryBuffer[49] = (byte)0xF0;    
				// A;                               
				m_memoryBuffer[50] = (byte)0xF0;    
				m_memoryBuffer[51] = (byte)0x90;    
				m_memoryBuffer[52] = (byte)0xF0;    
				m_memoryBuffer[53] = (byte)0x90;    
				m_memoryBuffer[54] = (byte)0x90;    
			    // B;		5                       
				m_memoryBuffer[55] = (byte)0xE0;    
				m_memoryBuffer[56] = (byte)0x90;    
				m_memoryBuffer[57] = (byte)0xE0;    
				m_memoryBuffer[58] = (byte)0x90;    
				m_memoryBuffer[59] = (byte)0xE0;    
				// C;                               
				m_memoryBuffer[60] = (byte)0xF0;    
				m_memoryBuffer[61] = (byte)0x80;    
				m_memoryBuffer[62] = (byte)0x80;    
				m_memoryBuffer[63] = (byte)0x80;    
				m_memoryBuffer[64] = (byte)0xF0;    
				// D;		6                           
				m_memoryBuffer[65] = (byte)0xE0;    
				m_memoryBuffer[66] = (byte)0x90;    
				m_memoryBuffer[67] = (byte)0x90;    
				m_memoryBuffer[68] = (byte)0x90;    
				m_memoryBuffer[69] = (byte)0xE0;    
				// E;                               
				m_memoryBuffer[70] = (byte)0xF0;    
				m_memoryBuffer[71] = (byte)0x80;    
				m_memoryBuffer[72] = (byte)0xF0;    
				m_memoryBuffer[73] = (byte)0x80;    
				m_memoryBuffer[74] = (byte)0xF0;    
				// F;		                            
				m_memoryBuffer[75] = (byte)0xF0;    
				m_memoryBuffer[76] = (byte)0x80;    
				m_memoryBuffer[77] = (byte)0xF0;    
				m_memoryBuffer[78] = (byte)0x80;    
				m_memoryBuffer[79] = (byte)0x80;    
	}
	
	
	
}
