package Chip8;

public interface Display {
	public byte getScreen(int index);
	public void setScreen(int index, byte value);
	public void print();
	public void clear();
}
