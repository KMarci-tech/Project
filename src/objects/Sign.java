package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Game;
import game.GameObject;
import game.ObjectId;
import game.Texture;

public class Sign extends GameObject{
	
	
	Texture text = Game.getInstance();

	public Sign(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(text.sign[0],(int)x,(int)y,32,32,null);
		
		
	//	g.setColor(Color.orange);
	//	g.fillRect((int)x,(int)y,32,32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,32,32);
	}

}
