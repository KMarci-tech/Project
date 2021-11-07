package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Game;
import game.GameObject;
import game.ObjectId;
import game.Texture;

public class Block extends GameObject{

	
	Texture text = Game.getInstance();
	private int type;
	
	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
	
		
		
		if(type == 0) {
			g.drawImage(text.block[0],(int)x,(int)y,null);
		}
		
		if(type == 1) {
			g.drawImage(text.block[1],(int)x,(int)y,null);
		}
		
	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,32,32);
	}

}
