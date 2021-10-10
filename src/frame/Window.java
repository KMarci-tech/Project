package frame;

import java.awt.*;

import javax.swing.JFrame;

public class Window {

	
	public Window(int w, int h, String title, Game game) { // sz�less�g, magass�g, ablak c�m, j�t�k fel�let
		
		game.setPreferredSize(new Dimension(w,h)); // fel�let m�retei
		
		JFrame frame = new JFrame(title); // ablak defini�l�sa
		
		frame.add(game); //j�t�k fel�leletnek a csatol�sa az ablakhoz
		frame.pack(); //m�retez�s az ablaknak
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ki lehessen l�pni
		frame.setResizable(false); // nem lehet �jram�retezni
		frame.setLocationRelativeTo(null); // a k�perny� k�zep�n jelenjen meg
		frame.setVisible(true); // l�that�
		
		game.start(); // sz�l elind�t�sa
		
		
	}
	
	
}
