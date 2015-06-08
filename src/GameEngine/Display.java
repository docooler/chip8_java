package GameEngine;

import Chip8.IDisplay;
public class Display implements IDisplay {
	static int SCREEN_SIZE = 256;
	static int SCREEN_HEIGH = 32;
	static int SCREEN_WITH  = 8;
	byte[] m_screenTest;
	byte [] m_screen;
	String []m_content;
	public Display(){
		m_screen = new byte[SCREEN_SIZE];
		m_content = new String[32];
		testData();
	}
	public byte getScreen(int index)
	{	
		return m_screen[index];
	}
	public void setScreen(int index, byte value)
	{
//		System.out.printf("setScreen index : %d, value: %d\n", index, value);
		m_screen[index] = value;
	}
	
	public void testData(){
		
		byte[] m_memoryBuffer;
		m_memoryBuffer = new byte[100];
		for (int i = 0 ; i < 100; i++){
			m_memoryBuffer[i] = 0;
		}
		// 0                                    
	    m_memoryBuffer[0] = (byte)0xF0;    
		m_memoryBuffer[1] = (byte)0x90;    
		m_memoryBuffer[2] = (byte)0x90;    
		m_memoryBuffer[3] = (byte)0x90;    
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
		
		m_screenTest = new byte[256];
		for (int i = 0; i < 16; i++){
			for (int j=0; j<5; j++){
				m_screenTest[i + j*8] = m_memoryBuffer[i*5+j]; 
			}
		}
		
	}
	
	public void drawOneByte(int x, int y, int bit, int value){
		return;
	}
	public void print()
	{
		for(int y = 0; y < SCREEN_HEIGH; y++){
			m_content[y] = ""; 
			for(int x = 0; x < SCREEN_WITH; x++){
				int screenIndex = y*SCREEN_WITH + x;
				//this is for test draw function;
				//byte value = m_screenTest[screenIndex];//TODO FOR DEBUG
				byte value = m_screen[screenIndex];
				int iValue = Byte.toUnsignedInt(value);
				int flag = 0x100;
				for(int i = 0 ; i < 8; i ++){//every byte is 8 bit
					flag >>= 1;
//					System.out.printf("flag : %d", flag);
					if((flag&iValue)>0){
						drawOneByte(x*8, y, i, 1);
					}else{
						drawOneByte(x*8, y, i, 0);
					}
				}
				
			}
		}
	}
	public void clear(){
		for (int i = 0; i< SCREEN_SIZE; i++){
			m_screen[i] = 0;
		}
	}

}
