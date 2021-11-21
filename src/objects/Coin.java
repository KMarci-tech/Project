package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Game;
import game.GameObject;
import game.ObjectId;
import game.Texture;

public class Coin extends GameObject{
	
	Texture text = Game.getInstance();
	

	public Coin(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(text.coin[0],(int)x,(int)y,17,17,null);
		
	//	g.setColor(new Color(185,213,30));
	//	g.fillRect((int)x,(int)y,24,24);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,12,12);
	}

}
