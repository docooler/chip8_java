package GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Chip8.IDisplay;

public class UI extends Display implements KeyListener {
	Canvas m_convas;
    
    TextField m_textField;
    KeyBoard m_keyboard;
    
	public UI(KeyBoard keyboard)  {
		 Frame f = new Frame("Canvas");
		 m_convas = new Canvas();
		 m_textField = new TextField();
		 
		 m_keyboard = keyboard;
		 
		 
		
		 f.add("Center", m_convas);
		 f.setSize(800, 600);
		 f.setVisible(true);
		 
		 
		 
	}
	
	void drawTest(){
		String[] str1 = { " *  *  *  * ",
						  " *        * ",
						  " *        * ",
						  " *        * ",
						  " *  *  *  * "};
		for (int i = 0; i< str1.length; i++){
			m_convas.getGraphics().drawString(str1[i], 50, 50+i*8);
		}
	}
	//实现KeyListener接口中的方法
    public void keyTyped(KeyEvent ev){
    	char key = ev.getKeyChar();
    }
    
    public void keyPressed(KeyEvent ev){ }
    
    public void keyReleased(KeyEvent ev){
     
    }
    
	public  void print(){
		
		System.out.println("UI print");
		super.print();
		for (int i = 0; i < SCREEN_HEIGH; i++){
			m_convas.getGraphics().drawString(m_content[i], 50, 50+i*8);
			
		}
		//drawTest();
	}
	public void clear(){
		System.out.println("UI print");
		super.clear();
		System.out.println("UI print");
		for (int i = 0; i < SCREEN_HEIGH; i++){
			m_convas.getGraphics().drawString(m_content[i], 50, 50+i*8);
		}
	}
}
