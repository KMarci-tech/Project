package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD{

	public int points;
	public int health;
	public int lifes = 3;
	public int ammo = 3;
	public int coins = 0;
	
	
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 23));
		g.drawString("Score: " + points, 20, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 23));
		g.drawString("Lifes: " + lifes, 20, 80);
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 23));
		g.drawString("Shurikens: " + ammo, 20, 110);

		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 23));
		g.drawString("You need to collect " + (5-coins) +  " coins!", 140, 50);
		
		
		g.setColor(Color.red);
		g.fillRect(120,60,100,25);
		
		g.setColor(Color.green);
		g.fillRect(120,60,(int)health,25);
		
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.drawString(health + "%", 150, 78);
	}
	
	public void gameover() {
		Window.close();
	}
	
	
	
	
	
	
	
}
