package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import game.GameObject;
import game.ObjectId;
import objects.AirBlock;
import objects.Ammunition;
import objects.Block;
import objects.Coin;
import objects.Enemy;
import objects.JumpingBlock;
import objects.Player;
import objects.Sign;
import objects.Spider;
import objects.WaterBlock;


public class Handler {

	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private HUD hud;
	
	Handler(Camera cam, HUD hud) {
		this.cam = cam;
		this.hud = hud;
	}
	public void update() {
		
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);		
			 
			tempObject.update(object);
		
		}
	}
	
	
	
	public void render(Graphics g) {
		
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);		
			 
			tempObject.render(g);
		
		}
	}
	
	
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 & blue == 255) { 
					addObject(new Block(xx*32,yy*32,1,ObjectId.Block));
				}
				
				if(red == 170 && green == 170 & blue == 170) {
					addObject(new Block(xx*32,yy*32,0,ObjectId.Block));
				}
				
				
				if(red == 0 && green == 0 & blue == 255) {
					addObject(new Player(xx*32,yy*32,this,cam,hud,ObjectId.Player));
				}
				
				if(red == 34 && green == 177 & blue == 76) {
					addObject(new JumpingBlock(xx*32,yy*32,ObjectId.JumpingBlock));
				}
				
				if(red == 63 && green == 72 & blue == 204) {
					addObject(new WaterBlock(xx*32,yy*32,ObjectId.WaterBlock));
				}
				
				if(red == 195 && green == 195 & blue == 195) {
					addObject(new AirBlock(xx*32,yy*32,ObjectId.AirBlock));
				}
				
				if(red == 237 && green == 28 & blue == 36) {
					addObject(new Enemy(xx*32,yy*32,this,ObjectId.Enemy));
				}
				
				if(red == 69 && green == 150 & blue == 41) {
					addObject(new Ammunition(xx*32,yy*32,ObjectId.Ammunition));
				}
				
				if(red == 255 && green == 242 & blue == 0) {
					addObject(new Spider(xx*32,yy*32,this,ObjectId.Spider));
				}
				
				if(red == 185 && green == 213 & blue == 30) {
					addObject(new Coin(xx*32,yy*32,ObjectId.Coin));
				}
				
				if(red == 177 && green == 130 & blue == 114) {
					addObject(new Sign(xx*32,yy*32,ObjectId.Sign));
				}
				
				
				
				
				
				
				
			}
		}
	}
	
	
	
	
	
	
	public void addObject(GameObject object) {
		
		
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearLevel() {
		object.clear();
	}
	
	
}
