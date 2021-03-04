import java.awt.Image;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bullet {
	
	private PApplet app;
	private int x, y, speed, damage;
	private PImage bullet;
	private boolean visible;

	PlayScreen play;
	
	public Bullet(PApplet app, int x, int y) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.speed = 25;
		this.visible = true;
		this.damage = 2;
		
		//Images
		bullet = app.loadImage("./data/Bullet.png");
	}
	
	public void draw() {
		if (visible == true) {
			app.imageMode(PConstants.CENTER);
			app.image(bullet, x, y, 30, 35);
		}
	}
	
	public void move() {
		y-=speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}


}
