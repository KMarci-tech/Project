package frame;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{

	private boolean running = false;
	private final double updateRate = 1.0d/60.0d;
	private Thread thread;
	
	
	private long nextStatTime;
	private int ups = 0; // update per seconds
	private int fps = 0; // frame per seconds
	
	
	public synchronized void start() { // elindítja a Game szálat
		
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	
	
	@Override
	public void run() { // a frissítések ütemezésének beállítása
		
		
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
	
	
	private void render() { //rajzol a képernyõre
		fps++;
	}
	
	private void update() { // állapot változás
		ups++;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Window(800,600,"Platformer",new Game());
		
	}
}
