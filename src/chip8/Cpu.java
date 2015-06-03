package chip8;

import java.util.Stack;
import java.util.Random;


public class Cpu {
	 Memory m_memory;
	 Display m_display;
	 KeyBoard m_keyBoard;
	 DelayTimer m_delayTimer;
	 DelayTimer m_soundTimer;
	 InstructionRun[] m_instructionSet;
	
	 char m_PC = (char)0x200;
	 byte[] m_register;  //16���Ĵ���
	 char m_I;          //��ַ�Ĵ���I
	 Stack m_stack;
	
	 boolean m_quit = false;
	 
	 Random m_random;
	
	
	
	public Cpu(Memory memory, Display display, KeyBoard keyBoard){

		//chip8
		m_memory = memory;
		m_display = display;
		m_keyBoard = keyBoard;
		
		
		m_delayTimer = new DelayTimer();
		m_soundTimer = new DelayTimer();
		m_register = new byte[16];
		m_stack = new Stack();
		
		//tools
		m_random = new Random();
		
		initInstructionFunc();
	}
	
	public byte GenRandom(){
		return (byte)m_random.nextInt(256);
	}
	
	private void initInstructionFunc(){
		m_instructionSet = new InstructionRun[16];
		m_instructionSet[0x0] = new InsSYS();
		m_instructionSet[0x1] = new InsJP(); 
		m_instructionSet[0x2] = new InsCALL();
		m_instructionSet[0x3] = new InsSE(); 
		m_instructionSet[0x4] = new InsSNE();
		m_instructionSet[0x5] = new InsSEV();
		m_instructionSet[0x6] = new InsLD(); 
		m_instructionSet[0x7] = new InsADD();
		m_instructionSet[0x8] = new InsREG();
		m_instructionSet[0x9] = new InsSNEV();
		m_instructionSet[0xA] = new InsLDI();
		m_instructionSet[0xB] = new InsJPV();
		m_instructionSet[0xC] = new InsRND();
		m_instructionSet[0xD] = new InsDRW();
		m_instructionSet[0xE] = new InsSKP();
		m_instructionSet[0xF] = new InsLDS();
	}
	
	public boolean processInstruction(char instruction){
		char opCode = (char)((int)instruction>>12);
		if (opCode > 0xf){
			System.out.printf("Error : wrong opcode: %d, insturction : %d\n", opCode, instruction);
		}
		InstructionRun iRun = m_instructionSet[opCode];
		return iRun.onRun(this, instruction);
	}
	
	public boolean run(){
		return true;
	}
	
	public void clock(){
		m_delayTimer.Tick();
		m_soundTimer.Tick();
		
		cycleRun();
	}
	private synchronized  void cycleRun(){
		char instruction = getNextInstruction();
		m_quit = processInstruction(instruction);
	}
	
	
	private char getNextInstruction(){
		byte hi =  m_memory.getValue(m_PC++);
		byte lo = m_memory.getValue(m_PC++);
		
		char instruction = (char)((int)hi << 8);
		instruction |= lo;
		return instruction;
	}
}


interface  InstructionRun {
	public boolean onRun(Cpu cpu, char instruction);
}

/*call back finish return*/
class InsRET implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		if (cpu.m_stack.isEmpty()){
			return true;
		}
		cpu.m_PC = (char)cpu.m_stack.pop();
		return false;
	}
}

/*
 * 0NNN	Calls RCA 1802 program at address NNN.
 * 00E0	Clears the screen.
 * 00EE	Returns from a subroutine.
 */
class InsSYS implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		char address = ParserFunctions.GetAddress(instruction);
		switch (address){
		case 0xee:
			InsRET ret = new InsRET();
			ret.onRun(cpu, instruction);
			return false;
		case 0xe0:
			cpu.m_display.clear();
			return false;
		case 0x00://����Ӧ��������ֵ����Ҫ�ڿ���
			return true;
		default:
			return true;
		}
	}
}

/*1NNN	Jumps to address NNN.*/

class InsJP implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		cpu.m_PC = ParserFunctions.GetAddress(instruction);
		return false; 
	}
}

/*
 * ����ָ������
 * 2NNN	Calls subroutine at NNN.*/
class InsCALL implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		cpu.m_stack.push(instruction);
		cpu.m_PC = ParserFunctions.GetAddress(instruction);
		return false; 
	}
}
/*3XNN	Skips the next instruction if VX equals NN.*/
class InsSE implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte opValue = ParserFunctions.GetValue(instruction);
		byte vx    = ParserFunctions.GetX(instruction);
		byte regValue = cpu.m_register[vx];
		if (regValue == opValue){
			cpu.m_PC += 2;
		}
		return false; 
	}
}
/*4XNN  Skips the next instruction if VX doesn't equal NN.*/
class InsSNE implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte opValue = ParserFunctions.GetValue(instruction);
		byte vx    = ParserFunctions.GetX(instruction);
		byte regValue = cpu.m_register[vx];
		if (regValue != opValue){
			cpu.m_PC += 2;
		}
		return false; 
	}
}

/*5XY0	Skips the next instruction if VX equals VY.*/
class InsSEV implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte vx = ParserFunctions.GetX(instruction);
		byte vy = ParserFunctions.GetY(instruction);
		byte regXValue = cpu.m_register[vx];
		byte regYValue = cpu.m_register[vy];
		if (regXValue == regYValue){
			cpu.m_PC += 2;
		}
		return false; 
	}
}
/*6XNN	Sets VX to NN. 
 * �����������������???????
 * �������������set nn TO VX�ˡ�*/
class InsLD implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte value = ParserFunctions.GetValue(instruction);
		byte vx    = ParserFunctions.GetX(instruction);
		cpu.m_register[vx] = value;
		return false; 
	}
}
/*7XNN	Adds NN to VX.*/
class InsADD implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte value = ParserFunctions.GetValue(instruction);
		byte vx    = ParserFunctions.GetX(instruction);
		cpu.m_register[vx] += value; 
		return false; 
	}
}
/*
8XY0	Sets VX to the value of VY.
8XY1	Sets VX to VX or VY.
8XY2	Sets VX to VX and VY.
8XY3	Sets VX to VX xor VY.
8XY4	Adds VY to VX. VF is set to 1 when there's a carry, and to 0 when there isn't.
8XY5	VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1 when there isn't.
8XY6	Shifts VX right by one. VF is set to the value of the least significant bit of VX before the shift.[2]
8XY7	Sets VX to VY minus VX. VF is set to 0 when there's a borrow, and 1 when there isn't.
8XYE	Shifts VX left by one. VF is set to the value of the most significant bit of VX before the shift.
*/
class InsREG implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte subfunction = ParserFunctions.GetSubCommand(instruction);
		byte vx = ParserFunctions.GetX(instruction);
		byte vy = ParserFunctions.GetY(instruction);
		
		
		switch (subfunction){
		case 0:
			cpu.m_register[vx] = cpu.m_register[vy];
			return false;
		case 1:
			cpu.m_register[vx] |= cpu.m_register[vy];
			return false;
		case 2:
			cpu.m_register[vx] &= cpu.m_register[vy];
			return false;
		case 3:
			cpu.m_register[vx] ^= cpu.m_register[vy];
			return false;
		case 4:
			cpu.m_register[0xf] = (byte)((cpu.m_register[vx] + cpu.m_register[vy]) > 0xff ? 1: 0);
			cpu.m_register[vx] += cpu.m_register[vy];
			return false;
		case 5:
			cpu.m_register[0xf] = (byte)(cpu.m_register[vx] > cpu.m_register[vy] ? 1: 0);
			cpu.m_register[vx] -= cpu.m_register[vy];
			return false;
		case 6:
			cpu.m_register[0xf] = (byte)(cpu.m_register[vx] & (byte)0x01);
			cpu.m_register[vx]  = (byte)(cpu.m_register[vx]>>1);
			return false;
		case 7:
			cpu.m_register[0xf] = (byte)(cpu.m_register[vx] < cpu.m_register[vy] ? 1 : 0);
			cpu.m_register[vx] = (byte) (cpu.m_register[vy] - cpu.m_register[vx]);
            return false;
		case 0x0e:
			cpu.m_register[0xf] = (byte)(cpu.m_register[vx] >> 7);
			cpu.m_register[vx] = (byte)(cpu.m_register[vx] << 1);
			return false;
		default:
			System.out.printf("Error REG subcommand %d", instruction);
			return true;
        }
	}
}
/*9XY0	Skips the next instruction if VX doesn't equal VY.*/
class InsSNEV implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte vx = ParserFunctions.GetX(instruction);
		byte vy = ParserFunctions.GetY(instruction);
		
		if(cpu.m_register[vx] != cpu.m_register[vy]){
			cpu.m_PC += 2;
		}
		return false; 
	}
}
/*ANNN	Sets I to the address NNN.*/
class InsLDI implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		char address = ParserFunctions.GetAddress(instruction);
		cpu.m_I = address;
		return false; 
	}
}
/*BNNN	Jumps to the address NNN plus V0.*/
class InsJPV implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		char address = ParserFunctions.GetAddress(instruction);
		byte offset = cpu.m_register[0];
		cpu.m_PC = (char)(address + offset);
		return false; 
	}
}
/*CXNN	Sets VX to a random number, masked by NN.*/
class InsRND implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte value = ParserFunctions.GetValue(instruction);
		byte random = cpu.GenRandom();
		
		byte vx = ParserFunctions.GetX(instruction);
		
		cpu.m_register[vx] = (byte)(random | value);
		return false; 
	}
}

/*DXYN	Sprites stored in memory at location in index register (I), 
 * maximum 8bits wide. Wraps around the screen. If when drawn, 
 * clears a pixel, register VF is set to 1 otherwise it is zero. 
 * All drawing is XOR drawing (i.e. it toggles the screen pixels)*/
class InsDRW implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte vx = ParserFunctions.GetX(instruction);
		byte vy = ParserFunctions.GetY(instruction);
		byte count = ParserFunctions.GetSubCommand(instruction);
		
		byte [] sprites = new byte[count];
		for(int i= 0; i< count; i++){
			sprites[i] = cpu.m_memory.getValue((char)(cpu.m_I+i));
		}
		
		//TODO set 0xf register???????
		
		cpu.m_display.print();
		return false; 
	}
}
/*
EX9E	Skips the next instruction if the key stored in VX is pressed.
EXA1	Skips the next instruction if the key stored in VX isn't pressed.
*/
class InsSKP implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte vx = ParserFunctions.GetX(instruction);
		byte value = ParserFunctions.GetValue(instruction);
		byte key = cpu.m_register[vx];
		switch (value){
		case (byte)0x9e:
			if (cpu.m_keyBoard.getValue(key)){
				cpu.m_PC += 2;
			}
			break;
			
		case (byte)0xA1:
			if (!cpu.m_keyBoard.getValue(key)){
				cpu.m_PC += 2;
			}
			break;
	     default :
	    	 System.out.printf("Error worong instruction %d", instruction);
	    	 break;
		}
		return false; 
	}
}
/*
 * FX07	Sets VX to the value of the delay timer.
 * FX0A	A key press is awaited, and then stored in VX.
 * FX15	Sets the delay timer to VX.
 * FX18	Sets the sound timer to VX.
 * FX1E	Adds VX to I.[3]
 * FX29	Sets I to the location of the sprite for the character in VX. 
		Characters 0-F (in hexadecimal) are represented by a 4x5 font.
 * FX33	Stores the Binary-coded decimal representation of VX, with the
 		most significant of three digits at the address in I, the middle
 		digit at I plus 1, and the least significant digit at I plus 2. 
 		(In other words, take the decimal representation of VX, place the
 		 hundreds digit in memory at location in I, the tens digit at location
 		 I+1, and the ones digit at location I+2.)
 * FX55	Stores V0 to VX in memory starting at address I.[4]
 * FX65	Fills V0 to VX with values from memory starting at address I.[4]
 */
class InsLDS implements InstructionRun {
	public boolean onRun(Cpu cpu, char instruction){
		byte vx = ParserFunctions.GetX(instruction);
		byte value = ParserFunctions.GetValue(instruction);
		byte regXValue = cpu.m_register[vx];
		switch (value){
		case (byte)0x07:
			cpu.m_delayTimer.set(regXValue);
			break;
		case (byte)0x0A:
			cpu.m_register[vx] = cpu.m_keyBoard.waitForValue();
		    break;
		}
		return false; 
	}
}

