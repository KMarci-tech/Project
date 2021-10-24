package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.kmarci.game.window.Handler;

public class Player extends GameObject{

	private float width = 48, height = 96;
	
	
	private float gravity = 0.5f;
	private float MaxSpeed = 10;
	
	private Handler handler;
	
	
	public Player(float x, float y,Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(inWater) {
			gravity = 0.1f;
			MaxSpeed = 2;
		}
		else {
			gravity = 0.5f;
			MaxSpeed = 10;
		}
		
		
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MaxSpeed)
				velY = MaxSpeed;
		}
	
		Collision(object);
		
	}

	private void Collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
			
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
					
				}
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
					setInWater(false);
					
				}
				else {
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 50;
					
					
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
					
					
				}
				
				
			}
			
			
			if(tempObject.getId() == ObjectId.JumpingBlock) {
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
					velY = -15;
					falling = false;
					jumping = false;
					
				}
				
				

				if(getBoundsRight().intersects(tempObject.getBounds())) {
					velY = -15;
					falling = false;
					jumping = false;
					
					
					
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					velY = -15;
					falling = false;
					jumping = false;
	
				}
				
			}
			
			if(tempObject.getId() == ObjectId.WaterBlock) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					falling = true;
					jumping = false;
					setInWater(true);
				//	System.out.println("Im in water");
	
				}
				
				
				
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					falling = true;
					jumping = false;
					setInWater(true);
	
				}
				
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					falling = true;
					jumping = false;
					setInWater(true);
	
				}
				
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					falling = true;
					jumping = false;
					setInWater(true);
	
				}
				
				
				
				
				
			}
			
			if(tempObject.getId() == ObjectId.AirBlock) {
				if(getBounds().intersects(tempObject.getBounds())) {
					setInWater(false);
				//	System.out.println("Im in air");
	
				}
			}
			
			
				
		}
			
			
			
	}
		
	
	
	
	
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,(int)width,(int)height);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
		
	}

	// height = 96 width = 48;
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+width/4), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+width/4), (int)y, (int)width/2, (int)height/2);
	}

	
	
	
	

}
