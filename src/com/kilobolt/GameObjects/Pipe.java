package com.kilobolt.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {
	private Random r;
	private Rectangle barUp, barDown;
	
	private static final int VERTICAL_GAP = 45;
	
	private float groundY;
	
	private boolean isScored = false;
	
	public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x, y, width, height, scrollSpeed);
		
		r = new Random();
		barUp = new Rectangle();
		barDown = new Rectangle();
		
		this.groundY = groundY;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width, groundY - (position.y + height + VERTICAL_GAP));
	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
		height = r.nextInt(90) + 15;
		isScored = false;
	}
	
	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}
	
	public Rectangle getBarUp() {
		return barUp;
	}
	
	public Rectangle getBarDown() {
		return barDown;
	}
	
	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp) 
					|| Intersector.overlaps(bird.getBoundingCircle(),  barDown));
		}
		
		return false;
	}
	
	public boolean isScored() {
		return isScored;
	}
	
	public void setScored(boolean b) {
		isScored = b;
	}
}
