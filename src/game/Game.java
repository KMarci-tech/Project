package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.KeyInput;
import game.ObjectId;
import objects.AirBlock;
import objects.Ammunition;
import objects.Block;
import objects.JumpingBlock;
import objects.Enemy;
import objects.Player;
import objects.Spider;
import objects.WaterBlock;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7241863992551144757L;

	private boolean running = false;
	private Thread thread;
	
	public static int HEIGHT, WIDTH;
	
	
	
	private BufferedImage level = null, forest = null;

	
	
	
	
	//Object
	Handler handler;
	Camera cam;
	HUD hud;
	static Texture text;
	
	Random rand = new Random();
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		
		text = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); // loading the level
		forest = loader.loadImage("/background.png");
		
		
		hud = new HUD();
		
		handler = new Handler();
		
		cam = new Camera(0,0);
		
		LoadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	
	public synchronized void start() {
		
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
		
	
	public void run() {
		
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfupdates = 60.0;
		double ns = 1000000000 / amountOfupdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0; 
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames + " updates: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
		
	}
	
	
	private void update() {
		handler.update();
		hud.update();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.Player) {
				cam.update(handler.object.get(i));
			}
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////
		
		
		
		//Draw here////////////////
		
		g.setColor(new Color(28,138,234));
		g.fillRect(0,0,getWidth(),getHeight());
		
		g.drawImage(forest,0,0,null);
		
		
		
		g2d.translate(cam.getX(),cam.getY()); //begin of camera
		
		
//		for(int xx = 0; xx < clouds.getWidth() * 3; xx += clouds.getWidth()) {
//			g.drawImage(clouds,xx,0,this);
//		}
		
		handler.render(g);
		
		
		
		
		
		
		g2d.translate(-cam.getX(),-cam.getY());
		
		hud.render(g);
		
		///////////////////
		g.dispose();
		bs.show();
		
	}
	
	private void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + " " + h);
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 & blue == 255) { 
					handler.addObject(new Block(xx*32,yy*32,1,ObjectId.Block));
				}
				
				if(red == 170 && green == 170 & blue == 170) {
					handler.addObject(new Block(xx*32,yy*32,0,ObjectId.Block));
				}
				
				
				if(red == 0 && green == 0 & blue == 255) {
					handler.addObject(new Player(xx*32,yy*32,handler,hud,ObjectId.Player));
				}
				
				if(red == 34 && green == 177 & blue == 76) {
					handler.addObject(new JumpingBlock(xx*32,yy*32,ObjectId.JumpingBlock));
				}
				
				if(red == 63 && green == 72 & blue == 204) {
					handler.addObject(new WaterBlock(xx*32,yy*32,ObjectId.WaterBlock));
				}
				
				if(red == 195 && green == 195 & blue == 195) {
					handler.addObject(new AirBlock(xx*32,yy*32,ObjectId.AirBlock));
				}
				
				if(red == 237 && green == 28 & blue == 36) {
					handler.addObject(new Enemy(xx*32,yy*32,handler,ObjectId.Enemy));
				}
				
				if(red == 64 && green == 128 & blue == 128) {
					handler.addObject(new Ammunition(xx*32,yy*32,ObjectId.Ammunition));
				}
				
				if(red == 255 && green == 242 & blue == 0) {
					handler.addObject(new Spider(xx*32,yy*32,handler,ObjectId.Spider));
				}
				
				
				
				
				
			}
		}
	}
	
	
	public static Texture getInstance() {
		return text;
	}
	
	
	public static void main(String args[]) {
		
		new Window(800,600,"Game platformer",new Game());
		
	}
	
	
}
