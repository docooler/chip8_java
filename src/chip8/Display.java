package Chip8;

public interface Display {
	public byte getScreen(byte index);
	public void setScreen(byte index, byte value);
	public void print();
	public void clear();
}
