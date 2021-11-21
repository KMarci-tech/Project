package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
import objects.Coin;
import objects.JumpingBlock;
import objects.Enemy;
import objects.Player;
import objects.Spider;
import objects.WaterBlock;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7241863992551144757L;

	private boolean running = false;
	private static Thread thread;
	
	public static int HEIGHT, WIDTH;
	
	public static int LEVEL = 1;
	
	
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
		cam = new Camera(0,0);
		handler = new Handler(cam,hud);
		
		
		
		handler.LoadImageLevel(level);
		
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
				System.out.println("FPS: " + frames + " updates: " + updates);
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
		
		
		
		g.drawImage(forest,0,0,null);
		
		
		g2d.translate(cam.getX(),cam.getY()); //begin of camera
		
		

		
		handler.render(g);
		

		g2d.translate(-cam.getX(),-cam.getY());
		
		hud.render(g);
		
		///////////////////
		g.dispose();
		bs.show();
		
	}
	
	
	
	
	
	
	public static Texture getInstance() {
		return text;
	}
	
	
	public static void main(String args[]) {
		new StartWindow();
		
	}
	
	
}
