package game;

import java.awt.*;

import javax.swing.JFrame;



public class Window {

	static JFrame frame;
	Game game;
	
	public Window(int w, int h, String title, Game game) {
				
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		
		
		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		game.start();
	}
	
	public static void close() {
		frame.dispose();
		new GameOverWindow();
	}
	
	
	
	
}
