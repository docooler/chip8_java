package Chip8;

public interface IKeyBoard {
	/*Fx0A - LD Vx, K
    Wait for a key press, store the value of the key in Vx.

    All execution stops until a key is pressed, then the value of that key is stored in Vx.*/
	byte waitForValue();
	boolean getValue(byte key);
}
