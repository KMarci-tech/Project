package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Animation;
import game.Game;
import game.GameObject;
import game.Handler;
import game.ObjectId;
import game.Texture;

public class Spider extends GameObject{
	
	private int width = 32; //26 32
	private int height = 22; //18 24
	
	private float gravity = 0.5f;
	private float MaxSpeed = 8;
	
	private boolean falling = true;
	private boolean jumping = false;
	
	
	private Handler handler;
	

	Texture text = Game.getInstance();
	
	

	public Spider(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		velX = 3;
	
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		if(getY() > 1000) {
			handler.removeObject(this);
		}
		
		
		x += velX;
		y += velY;
		
		
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MaxSpeed)
				velY = MaxSpeed;
		}
		Collision(object);

		
		
	}
	
	private void Collision(LinkedList<GameObject> object) {
		
	try {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
			
			if(tempObject.getId() == ObjectId.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - 18; 
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 18; 
					velY = 0;
					
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					velY = -5;
					velX = -3;
					
					
				}

				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
					velY = -5;
					velX = 3;
					
					
				}
				

				}
			
			if(tempObject.getId() == ObjectId.AirBlock) {
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					velY = -5;

				}

				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					velY = -5;
					
					
				}

			}

		}
		
	}catch(IndexOutOfBoundsException e) {
		System.out.println("ERROR SPIDER");
	}
	}		
	
	
	

	@Override
	public void render(Graphics g) {
		
		if(velX > 0){
			g.drawImage(text.spider[0],(int)x,(int)y,32,24,null);
		}
		else {
			g.drawImage(text.spider[1],(int)x,(int)y,32,24,null);
		}
		
		
		

	// Hitbox	
	/*	
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	*/	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+width/4), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)2, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)2, (int)height-10);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+width/4), (int)y, (int)width/2, (int)height/2);
	}
	
	
	
	public boolean isFalling() {
		return falling;
	}


	public void setFalling(boolean falling) {
		this.falling = falling;
	}


	public boolean isJumping() {
		return jumping;
	}


	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	

}
