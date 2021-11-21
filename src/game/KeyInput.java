package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.GameObject;

import game.Handler;
import objects.Bullet;
import objects.Player;

public class KeyInput extends KeyAdapter{

	Handler handler;
	
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
		
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				
				Player p = (Player)tempObject;
				
				
				if(key == KeyEvent.VK_D) {
					p.setVelX(5);
				}
				if(key == KeyEvent.VK_A) {
					p.setVelX(-5);
				}
				if(key == KeyEvent.VK_W && !p.isJumping()) {
					
					
					if(p.inWater()) {
						p.setJumping(true);
						p.setVelY(-5);
					}
					else {
						p.setJumping(true);
						p.setVelY(-10);
					}
					
					
				}
				
				
				
				// 
				if(key == KeyEvent.VK_SPACE && !p.isShot() && p.getAmmo() > 0) { 
					handler.addObject(new Bullet(tempObject.getX(),tempObject.getY() + 40, handler,ObjectId.Bullet, p.getFacing() * 10));
					p.setShot(true);
					p.setAmmo(-1);
				
					
					
					
					
				}
			}
			
		}
		
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				
				
				Player p = (Player)tempObject;
				
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
				
				if(key == KeyEvent.VK_SPACE) {
					p.setShot(false);
				}
			}
		
		
	}

 }
}

