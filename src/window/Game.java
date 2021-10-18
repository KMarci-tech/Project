package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import frame.KeyInput;

import frame.Handler;
import frame.ObjectId;
import objects.Player;


public class Game extends Canvas implements Runnable{

	private boolean running = false;
	private final double updateRate = 1.0d/60.0d;
	private Thread thread;
	
	
	public static int WIDTH, HEIGHT;
	
	private long nextStatTime;
	private int ups = 0; // update per seconds
	private int fps = 0; // frame per seconds
	
	
	//Object
	
	Handler handler;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		handler.addObject(new Player(100,100,handler,ObjectId.Player));
		
		handler.createLevel();
		this.addKeyListener(new KeyInput(handler));
	}
	
	
	public synchronized void start() { // start thread
		
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	
	
	@Override
	public void run() { // game loop
		
		init();
		this.requestFocus();
		double accumulator = 0;
		long currentTime = System.currentTimeMillis();
		long lastUpdate = System.currentTimeMillis();
		nextStatTime = System.currentTimeMillis() + 1000;
		
		while(running) {
			currentTime = System.currentTimeMillis();
			double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
			accumulator += lastRenderTimeInSeconds;
			lastUpdate = currentTime;
			
			
			while(accumulator > updateRate) {
				update();
				accumulator -= updateRate;
			}
			render();
			printStats();
		}
		
	}
	
	private void printStats() {
		if(System.currentTimeMillis() > nextStatTime) {
			System.out.println("FPS: " + fps + " UPS: " + ups);
			fps = 0;
			ups = 0;
			nextStatTime = System.currentTimeMillis() + 1000;
		}
	}
	
	
	private void render() { 
		fps++;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////
		//Draw Here
		
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		
		handler.render(g);
		
		
		///////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	private void update() {
		ups++;
		handler.update();
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Window(800,600,"Platformer",new Game());
		
	}
}
