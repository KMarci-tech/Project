package game;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartWindow implements ActionListener{

	JFrame frame = new JFrame();
	JButton startButton = new JButton("Start");
	JButton exitButton = new JButton("Exit");
	ImageIcon background = new ImageIcon(this.getClass().getResource("/window_background.png"));
	ImageIcon icon = new ImageIcon(this.getClass().getResource("/logo.png"));
	JLabel backgroundLabel;
	JLabel title = new JLabel();
	
	
	
	StartWindow(){
		
		startButton.setBounds(150,220,200,40);
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		exitButton.setBounds(150,300,200,40);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
				
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(520,520);
		
		
		title.setText("Ninja Platformer");
		title.setBounds(165,0,250,250);
		title.setForeground(new Color(225,137,4));
		title.setFont(new Font(null,Font.BOLD,30));
		
		
		frame.setTitle("Ninja Platformer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520,520);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(new JLabel(new ImageIcon("/window_background.png")));
		
		
		frame.add(startButton);
		frame.add(exitButton);
		frame.add(title);
		frame.add(backgroundLabel);
		
		
		frame.setIconImage(icon.getImage());
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton) {
			frame.dispose();
			new Window(800,600,"Game platformer",new Game());
			
		}
		
		
		if(e.getSource() == exitButton) {
			System.exit(1);
			
		}
		
	}
	
	
}
