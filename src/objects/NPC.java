package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.GameObject;
import game.HUD;
import game.Handler;
import game.ObjectId;

public class NPC extends GameObject{
	
	private float width = 52, height = 96;
	private float gravity = 0.5f;
	private float MaxSpeed = 8;
	protected float maxRight = x + 40;
	protected float maxLeft = x - 40;
	protected float range;
	private float speed = 4.0f;
	private static int npc_killed = 0;
	
	
	private Handler handler;
	
	public NPC(float x, float y,Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		
		
		if(x > range) {
			range = maxLeft;
		}
		else {
			range = maxRight;
		}
		
		if(x < range) {
			velX = speed;
			x += velX;
			y += velY;
		}
		else if(x > range){
			velX = speed;
			x -= velX;
			y += velY;
			
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
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				//	setInWater(false);
					
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
			
			if(tempObject.getId() == ObjectId.Bullet) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					System.out.println("Im in Bounds");
					npc_killed++;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					System.out.println("Im in RightBounds");
					npc_killed++;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
					
				}
				
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					System.out.println("Im in LeftBounds");
					npc_killed++;
					System.out.println(getKilled());
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
	
				}
				
			}
			
			
		}
	}
	
	
	
	

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,(int)width,(int)height);
		
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.blue);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
		
	}

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
	
	
	public float getMaxLeft() {
		return maxLeft;
	}
	
	public static int getKilled() {
		return npc_killed;
	}
	
	public static void setKilled(int n) {
		npc_killed += n;
	}
	
	
	}

	

	


