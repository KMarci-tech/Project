package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.GameObject;
import game.Handler;
import game.ObjectId;

public class Ammunition extends GameObject{

	
	
	
	public Ammunition(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	
	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(64,128,128));
		g.fillRect((int)x,(int)y,16,16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,16,16);
	}

}
