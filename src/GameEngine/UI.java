package GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Chip8.IDisplay;

public class UI extends Display implements KeyListener  {
	int MAX_X = 64;
	int MAX_Y = 32;
	int [][] map = new int [64][32];
    KeyBoard m_keyboard;
    MyDrawP m_drawp;

	public UI(KeyBoard keyboard)  {
		 JFrame frame = new JFrame();
		 JMenuBar menu = new JMenuBar();
	     frame.setJMenuBar(menu);
	     JMenu game = new JMenu("游戏");
	     JMenuItem newgame = game.add("新游戏");
	     JMenuItem pause = game.add("暂停");
	     JMenuItem goon = game.add("继续");
	     JMenuItem exit = game.add("退出");
	     JMenu help = new JMenu("帮助");
	     JMenuItem about = help.add("关于");
	     menu.add(game);
	     menu.add(help);
	     
	
	     MyDrawP m_drawp = new MyDrawP();
	     frame.getContentPane().add(m_drawp);
	     frame.add(m_drawp);
	     frame.setLocationRelativeTo(null);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(640,600);
	     frame.setTitle("GameBoy Chip8");
	        // frame.setUndecorated(true);
	     frame.setVisible(true);
	     frame.setResizable(false);
		 
		 
	}
	
    class MyDrawP extends JPanel {
    	public MyDrawP(){
    		System.out.println("call MyDrawP create function");
    	}
    	public void paintComponent (Graphics g){
    		for (int x=0; x<MAX_X; x++){
    			for (int y=0; y<MAX_Y; y++){
    				if (map[x][y] == 1){
    					g.setColor(Color.blue);
    					g.drawRect(x*10, y*10, 10, 10);
    					g.fillRect(x*10, y*10, 10, 10);
    				}
    			}
    		}
    	}
    }
	
    public void print(){
    	super.print();
    	m_drawp.repaint();
    }
    public void clear(){
    	super.clear();
    	for (int x=0; x<MAX_X; x++){
			for (int y=0; y<MAX_Y; y++){
				map[x][y] = 0;
			}
		}
    	m_drawp.repaint();
    }
	public void drawOneByte(int x, int y, int bit, int value){
		map[x+bit][y] = value;
		return;
	}
	
//	实现KeyListener接口中的方法
	public void keyTyped(KeyEvent ev){
		char key = ev.getKeyChar();
	}
  
	public void keyPressed(KeyEvent ev){ }
  
	public void keyReleased(KeyEvent ev){
   
	}
}


