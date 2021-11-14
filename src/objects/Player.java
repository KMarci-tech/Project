package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JProgressBar;

import game.Animation;
import game.Game;
import game.GameObject;
import game.HUD;
import game.ObjectId;
import game.Texture;
import game.Handler;


public class Player extends GameObject{

	private float width = 42, height = 76;
	
	
	private float gravity = 0.5f;
	private float MaxSpeed = 10;
	private float startX = x;
	private float startY = y;
	private int facing = 1;
	
	private boolean falling = true;
	private boolean jumping = false;
	private boolean inWater = false;
	
	private boolean shot = false;
	
	
	
	
	private HUD hud;

	private Handler handler;

	private Animation playerWalk, playerWalkLeft;
	
	Texture text = Game.getInstance();
	
	public Player(float x, float y,Handler handler, HUD hud, ObjectId id) {
		super(x, y, id);
		this.hud = hud;
		//hud.setHealth(100);
		hud.health = 100;
		this.handler = handler;
		
		playerWalk = new Animation(12,text.player[0],text.player[1]);
		playerWalkLeft = new Animation(12,text.player[3],text.player[2]);
		
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		
		
		if(getY() > 1000 || hud.health <= 0){
			dead();
		}
		
		if(Enemy.getKilled() > 0) {
			hud.points += 10;
			Enemy.setKilled(-1);
		}
		
		x += velX;
		y += velY;
		
		if(velX < 0) {
			facing = -1;
		}
		else if(velX > 0){
			facing = 1;
		}
		
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
		
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		
	}

	private void Collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
			
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height/2; 
					velY = 0;
					
				}
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height+1;
					velY = 0;
					falling = false;
					jumping = false;
					
				}
				else {
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width+2;
					
					
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 30; // + width
					
					
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
				
	
				}
			}
			
			
			if(tempObject.getId() == ObjectId.Enemy) {
				
			
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 28;
					hud.health += -4;

				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 40;
					hud.health += -4;
					
					
					
				}
			}
			
			if(tempObject.getId() == ObjectId.Ammunition) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
				//	ammo += 3;
					handler.removeObject(tempObject);
					
					
				}
				
				
				
				
			}
			
			if(tempObject.getId() == ObjectId.Spider) {
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 30;
					velX = 0;
					hud.health += -3;
				}
				
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 30;
					velX = 0;
					hud.health += -3;
					
				}
				
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					
					
					
				}
				
				
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.points += 5;
					
					
					
				}
				
				
				
				
				}
			
			
				
		}
			
			
			
	}
		
	public void dead() {
		
		if(hud.lifes == 1) {
			System.out.println("Game Over");
			System.exit(1);			
		}
		else {
			handler.removeObject(this);
			hud.lifes--;
			handler.addObject(new Player(startX,startY,handler,hud,ObjectId.Player));
			System.out.println("Health left: " + hud.lifes);
		}
		
		
	}
	
	
	
	
	@Override
	public void render(Graphics g) {
		
		
		if(jumping) {
			if(facing == 1) {
				g.drawImage(text.player_jump[0],(int)x,(int)y,42,80,null);
			}
			else {
				g.drawImage(text.player_jump[1],(int)x,(int)y,42,80,null);
			}
		}
		else {
			if(velX != 0) {
				if(facing == 1) {
					playerWalk.drawAnimation(g,(int)x,(int)y,42,80);
				}
				else {
					playerWalkLeft.drawAnimation(g,(int)x,(int)y,42,80);
				}
			}
			else {
				
				if(facing == 1) {
					g.drawImage(text.player[0],(int)x,(int)y,42,80,null);
				}
				else {
					g.drawImage(text.player[3],(int)x,(int)y,42,80,null);
				}
				
			}
		}
		
		
		

	// Hitbox	
		
/*		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	*/	
	}

	
	public void setAmmo(int ammo) {
		hud.ammo += ammo;
	}
	
	public int getAmmo() {
		return hud.ammo;
	}
	
	public void setPoints(int points) {
		hud.points = points;
	}
	
	public int getPoints() {
		return hud.points;
	}
	
	public void setHealth(int health) {
		hud.health = health;
	}
	
	public int getHealth() {
		return hud.health;
	}
	
	public void setLifes(int life) {
		hud.lifes += life;
	}
	
	public float getLifes() {
		return hud.lifes;
	}
	
	public boolean isShot() {
		return shot;
	}
	
	public void setShot(boolean shot) {
		this.shot = shot;
	}
	
	public int getFacing() {
		return facing;
	}
	
	public void setFacing(int facing) {
		this.facing = facing;
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
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+width/4), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5)-5, (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) (int)x+5, (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+width/4), (int)y, (int)width/2, (int)height/2);
	}
	
	

	
	

}
