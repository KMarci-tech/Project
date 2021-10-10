package frame;

import java.awt.*;

import javax.swing.JFrame;

public class Window {

	
	public Window(int w, int h, String title, Game game) { // szélesség, magasság, ablak cím, játék felület
		
		game.setPreferredSize(new Dimension(w,h)); // felület méretei
		
		JFrame frame = new JFrame(title); // ablak definiálása
		
		frame.add(game); //játék felüleletnek a csatolása az ablakhoz
		frame.pack(); //méretezés az ablaknak
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ki lehessen lépni
		frame.setResizable(false); // nem lehet újraméretezni
		frame.setLocationRelativeTo(null); // a képernyõ közepén jelenjen meg
		frame.setVisible(true); // látható
		
		game.start(); // szál elindítása
		
		
	}
	
	
}
