package chip8;

public interface IKeyBoard {
	byte waitForValue();
	boolean getValue(byte key);
}
