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
    JFrame frame;

	public UI(KeyBoard keyboard)  {
		 frame = new JFrame();
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
	}
	
	public void alive(){
		AllRun run = new AllRun(this);
		run.start();
	}
	
	class AllRun extends Thread {
		UI m_ui;
		public AllRun(UI ui){
			m_ui = ui;
		}
		public void run(){
			  m_ui.m_drawp = new MyDrawP();
			  m_ui.frame.getContentPane().add(m_drawp);
			  m_ui.frame.add(m_drawp);
//			     frame.setLocationRelativeTo(null);
			  m_ui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  
			  m_ui.frame.setSize(640,600);
			  m_ui.frame.setTitle("GameBoy Chip8");
			        // frame.setUndecorated(true);
			  m_ui.frame.setVisible(true);
			  m_ui.frame.setResizable(false);
			  
			while(true){
				//we just keep this tread alive. so we can keep the drawp instance. if
				//this thread finished the drawp will be release. so I write here so urgly.
				try {
					Thread.sleep(5000000);
					System.out.println("All run system.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
    class MyDrawP extends JPanel {
    	 int SIZE = 10;
    	
    	public MyDrawP(){
    		System.out.println("call MyDrawP create function");
    	}
    	public void paintComponent (Graphics g){
    		super.paintComponent(g);
    		
    		for (int x=0; x<MAX_X; x++){
    			for (int y=0; y<MAX_Y; y++){
    				
    				g.drawRect(x*SIZE, y*SIZE, SIZE, SIZE);
    				if (map[x][y] == 1){
    					g.setColor(Color.blue);
    					g.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
    				}else{
    					g.setColor(Color.white);
    					g.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
    				}
    			}
    		}
    	}
    }
	
    public void print(){
    	//super.print();
    	m_drawp.repaint();
    }
    public void clear(){
//    	super.clear();
    	for (int x=0; x<MAX_X; x++){
			for (int y=0; y<MAX_Y; y++){
				map[x][y] = 0;
			}
		}
    	m_drawp.repaint();
    }
	public void drawOneByte(int x, int y, int bit, int value){
//		if (y>= 5 && value == 1){
//			System.out.printf("dfdopfdspofx:%d, y:%d\n",x,y);
//		}
		map[x+bit][y] = value;
		return;
	}
	public byte getScreen(int x, int y){
		return (byte)map[x][y];
	}
	
	public void setScreen(int x, int y, int value){
		map[x][y] ^= value;
	}
//	实现KeyListener接口中的方法
	public void keyTyped(KeyEvent ev){
		char key = ev.getKeyChar();
	}
  
	public void keyPressed(KeyEvent ev){ }
  
	public void keyReleased(KeyEvent ev){
   
	}
}


