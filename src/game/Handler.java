package game;

import java.awt.Graphics;
import java.util.LinkedList;

import game.GameObject;
import game.ObjectId;


public class Handler {

	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
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
	
	
	public void addObject(GameObject object) {
		
		
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	
	
}
