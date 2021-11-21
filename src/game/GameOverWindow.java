package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOverWindow implements ActionListener{

	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JButton restartButton = new JButton("Restart");
	JButton exitButton = new JButton("Exit");
	ImageIcon background = new ImageIcon(this.getClass().getResource("/window_background.png"));
	ImageIcon icon = new ImageIcon(this.getClass().getResource("/logo.png"));
	JLabel backgroundLabel;
	
	
	GameOverWindow() {
		
		label.setText("GAME OVER");
		label.setBounds(165,0,200,250);
		label.setForeground(new Color(225,137,4));
		label.setFont(new Font(null,Font.BOLD,30));
		
		restartButton.setBounds(150,220,200,40);
		restartButton.setFocusable(false);
		restartButton.addActionListener(this);
		
		exitButton.setBounds(150,300,200,40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(520,520);
		
		frame.setTitle("Ninja Platformer");
		
		frame.add(label);
		frame.add(restartButton);
		frame.add(exitButton);
		frame.add(backgroundLabel);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520,520);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == restartButton) {
			frame.dispose();
			new Window(800,600,"Game platformer",new Game());
			
		}
		
		
		if(e.getSource() == exitButton) {
			System.exit(1);
			
		}
		
	}
	
	
}
