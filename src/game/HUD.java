package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public int points;
	private float health;
	
	
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23));
		g.drawString("Score: " + points, 60, 70);
		
		
		g.setColor(Color.red);
		g.fillRect(200,50,100,25);
		
		g.setColor(Color.green);
		g.fillRect(200,50,(int)health,25);
		
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 13));
		g.drawString("Health: " + health + "%", 200, 70);
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public float getHealth() {
		return health;
	}
	
	
}
