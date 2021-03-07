import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Spaceship {
	
	private PApplet app;
	private PImage ship, bullet;
	private int x, y, coolDown, speed, width, height;
	private ArrayList<Bullet> bullets;
	
	public Spaceship(PApplet app) {
		this.app = app;
		this.x = 600;
		this.y = 570;
		this.speed = 30;
		this.coolDown = 0;
		this.width = 70;
		this.height = 130;
		
		//Images
		ship = app.loadImage("./data/Spaceship.png");			bullet = app.loadImage("./data/Bullet.png");
		
		//Class and ArrayList
		bullets = new ArrayList <>();
		
	}
	
	public void draw() {
		app.imageMode(PConstants.CENTER);
		app.image(ship, x, y, width, height);
		
		//CoolDown 
		if (coolDown > 0) {
			coolDown--;
		}	
	}
	
	public void moveShipLeft() {
		x-=speed;
		
		if (x < 0) {
			x = 20;
		}
	}
	
	public void moveShipRight() {
		x+=speed;
		
		if (x > 1200) {
			x = 1180;
		}
	}
	
	public void generateBullets() {
		if (coolDown == 0) {
			Bullet bullet = new Bullet(app, x, y);
			bullets.add(bullet);
			coolDown = 10;
		}
	}
	
	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(bullet);
			bullets.get(i).move();
		}
	}
	
	public void eliminateBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getY() < 0) {
				bullets.remove(i);
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
