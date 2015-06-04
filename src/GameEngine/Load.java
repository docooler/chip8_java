package GameEngine;
import Chip8.*;

import java.io.*;
import java.util.Arrays;

public class Load {
	/*this file only include the number table from 0 to f*/
	private String romFile = "C:\\workspace\\java\\chip8_java\\c8games\\ROM.bin";
	private String gameFileDir = "C:\\workspace\\java\\chip8_java\\c8games\\";
	static int READSIZE = 512;
	static int MAX_PROGRAM_SIZE = 3584;
	
	
	public  Load(){
		 
	}
	
	public boolean loadRomFromFile(Memory memory){
		
		File romFd = new File(romFile);
		byte[] tempbytes = new byte[256];
		byte[] romBuffer = new byte[READSIZE];
		InputStream in = null;
		try {
            
            int byteread = 0;
            int offset   = 0;
            in = new FileInputStream(romFile);
            
            while ((byteread = in.read(tempbytes)) != -1){
            	
            	if (offset < READSIZE){
            		System.arraycopy(tempbytes, 0, romBuffer, offset, READSIZE-offset>byteread?byteread:READSIZE-offset);
            		offset += READSIZE-offset>byteread?byteread:READSIZE-offset;
            		
            	}else{
            		break;
            	}
            }
            	
            //System.out.println(offset);
     
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
		
		//System.out.println(tempbytes);
		memory.initalizeRom(tempbytes);
		
		return true;
	}
	
	public boolean loadGame(String gamefile, Memory memory){
		
		String fullFilePath = gameFileDir + gamefile;
		
		System.out.print("game rom is ");
		System.out.println(fullFilePath);
		
		File romFd = new File(fullFilePath);
		byte[] tempbytes = new byte[1024];
		byte[] programBuffer = new byte[MAX_PROGRAM_SIZE];
		InputStream in = null;
		try {
            
            int byteread = 0;
            int offset   = 0;
            in = new FileInputStream(fullFilePath);
            
            while ((byteread = in.read(tempbytes)) != -1){
            	
            	if (offset < MAX_PROGRAM_SIZE){
            		System.arraycopy(tempbytes, 0, programBuffer, offset, MAX_PROGRAM_SIZE-offset>byteread?byteread:MAX_PROGRAM_SIZE-offset);
            		offset += MAX_PROGRAM_SIZE-offset>byteread?byteread:MAX_PROGRAM_SIZE-offset;
            		
            	}else{
            		break;
            	}
            }
            //System.out.println(offset);
             
            
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
		
		
		memory.loadProgram(programBuffer);
		
		return true;
	}

}
