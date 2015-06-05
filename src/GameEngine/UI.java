package GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Chip8.IDisplay;

public class UI extends Display {
	Canvas m_convas;
    String m_content;
    TextField m_textField;
    
	public UI(){
		 Frame f = new Frame("Canvas");
		 m_convas = new Canvas();
		 m_textField = new TextField();
		 
		 
		 m_content = "*********************************";
		 f.add("Center", m_convas);
		 f.setSize(800, 600);
		 f.setVisible(true);
		 
	}
	
	public  void print(){
		super.print();
		System.out.println("UI print");
		for (int i = 0; i < SCREEN_HEIGH; i++){
			m_convas.getGraphics().drawString(m_content, 50, i);
		}
		
		
	}
	public void clear(){
		System.out.println("UI print");
		super.clear();
		System.out.println("UI print");
		for (int i = 0; i < SCREEN_HEIGH; i++){
			m_convas.getGraphics().drawString(m_content, 50, i);
		}
	}
}
