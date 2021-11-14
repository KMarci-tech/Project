package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Animation;
import game.Game;
import game.GameObject;
import game.Handler;
import game.ObjectId;
import game.Texture;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	Texture text = Game.getInstance();
	
	private Animation shuriken_throw;
	
	public Bullet(float x, float y,  Handler handler,ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		this.handler = handler;
		shuriken_throw = new Animation(1,text.shuriken[0],text.shuriken[1]);
	}

	
	private void Collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
			
			if(tempObject.getId() == ObjectId.Block) {
				
				if(getBounds().intersects(tempObject.getBounds()))  {
					handler.removeObject(this);
					
					
				}
				
			}

			
	    }
	}
	
	
	
	
	
	@Override
	public void update(LinkedList<GameObject> object) {
		x += velX;
		Collision(object);
		shuriken_throw.runAnimation();
		
	}

	@Override
	public void render(Graphics g) {
		
		shuriken_throw.drawAnimation(g,(int)x,(int)y,17,17);
		
		
	//	g.drawImage(text.shuriken[1],(int)x,(int)y,17,17,null);
		
	/*
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,16,16);
	*/	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,17,17);
	}

}
