package Chip8;

public interface IDisplay {
	public byte getScreen(int index);
	public void setScreen(int index, byte value);
	public void print();
	public void clear();
	public byte getScreen(int x, int y);
	public void setScreen(int x, int y, int value);
}
