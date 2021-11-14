package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.GameObject;
import game.ObjectId;

public class AirBlock extends GameObject{

	public AirBlock(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
	
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,32,32);
	}

}
