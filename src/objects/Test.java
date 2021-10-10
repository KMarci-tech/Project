package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import frame.GameObject;
import frame.ObjectId;

public class Test extends GameObject{

	public Test(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y,20,20);
		
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
		
	}

	@Override
	public void setY(float y) {
		this.y = y;
		
	}

	@Override
	public float getVelX() {
		return velX;
	}

	@Override
	public float getVelY() {
		return velY;
	}

	@Override
	public void setVelX(float velX) {
		this.velX = velX;
		
	}

	@Override
	public void setVelY(float velY) {
		this.velY = velY;
		
	}

	@Override
	public ObjectId getId() {
		return id;
	}

}
