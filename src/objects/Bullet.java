package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


import game.GameObject;
import game.Handler;
import game.ObjectId;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	public Bullet(float x, float y,  Handler handler,ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		this.handler = handler;
	}

	
	private void Collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
			
			if(tempObject.getId() == ObjectId.Block) {
				
				if(getBounds().intersects(tempObject.getBounds()))  {
					handler.removeObject(this);
					
					
				}
				
			}
		
			/*
			if(tempObject.getId() == ObjectId.NPC) {
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
						hud.setPoints(hud.getPoints() + 5);
						handler.removeObject(this);
						handler.removeObject(tempObject);
						
						
					}
					
					
				
			}
			*/
			

			
			
	    }
	}
	
	
	
	
	
	@Override
	public void update(LinkedList<GameObject> object) {
		x += velX;
		Collision(object);
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,16,16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,16,16);
	}

}
