package Chip8;

public interface IDisplay {
	public byte getScreen(int index);
	public void setScreen(int index, byte value);
	public void print();
	public void clear();
}
