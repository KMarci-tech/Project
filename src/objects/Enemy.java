package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Animation;
import game.Game;
import game.GameObject;
import game.HUD;
import game.Handler;
import game.ObjectId;
import game.Texture;

public class Enemy extends GameObject{
	
	private float width = 50, height = 86; // 42, 76
	private float gravity = 0.5f;
	private float MaxSpeed = 8;
	protected float maxRight = x + 40;
	protected float maxLeft = x - 40;
	protected float range;
	private float speed = 1.5f;
	private static int enemy_killed = 0;
	
	private boolean falling = true;
	private boolean jumping = false;
	private boolean inWater = false;
	
	
	private Handler handler;
	
	private Animation enemyWalk, enemyWalkLeft;
	
	Texture text = Game.getInstance();
	
	public Enemy(float x, float y,Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		enemyWalk = new Animation(12,text.enemy[2],text.enemy[3]);
		enemyWalkLeft = new Animation(12,text.enemy[0],text.enemy[1]);
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
			velX = -speed;
			x += velX;
			y += velY;
			
		}
		
	
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MaxSpeed)
				velY = MaxSpeed;
		}
		
		
		
		
		Collision(object);
		enemyWalk.runAnimation();
		enemyWalkLeft.runAnimation();
		
	}
	
	
	private void Collision(LinkedList<GameObject> object) {
		
	try {
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
			
			
			if(tempObject.getId() == ObjectId.WaterBlock) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					speed = 0.4f;
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
					enemy_killed++;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					enemy_killed++;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
					
				}
				
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					enemy_killed++;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					
	
				}
				
			}
			
			
		}
	}catch(IndexOutOfBoundsException e) {
		System.out.println("ERROR ENEMY");
	}
	}
	
	
	
	

	@Override
	public void render(Graphics g) {
		
		
		if(velX > 0){
			enemyWalkLeft.drawAnimation(g,(int)x,(int)y,50,90);
		}
		else {
			enemyWalk.drawAnimation(g,(int)x,(int)y,50,90);
		}
		
		
		
		
		
		
		
	//	g.setColor(Color.red);
	//	g.fillRect((int)x,(int)y,(int)width,(int)height);
		
	/*	
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
		*/
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
		return enemy_killed;
	}
	
	public static void setKilled(int n) {
		enemy_killed += n;
	}
	
	public boolean inWater() {
		return inWater;
	}
	
	public void setInWater(boolean inWater) {
		this.inWater = inWater;
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

	

	


