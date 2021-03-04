import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Spaceship {
	
	private PApplet app;
	private PImage ship;
	private int x, y, life, coolDown, speed;
	private ArrayList<Bullet> bullets;
	
	public Spaceship(PApplet app) {
		this.app = app;
		this.x = 600;
		this.y = 570;
		this.life = 25;
		this.speed = 30;
		this.coolDown = 0;
		this.life = 30; //Every 6 points eliminated, 1 heart is eliminated
		
		
		//Images
		ship = app.loadImage("./data/Spaceship.png");
		
		//Class and ArrayList
		bullets = new ArrayList <>();
		
	}
	
	public void draw() {
		app.imageMode(PConstants.CENTER);
		app.image(ship, x, y, 70, 130);
		
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
			bullets.get(i).draw();
			bullets.get(i).move();
		}
	}
	
	public void eliminateBullet() {
		
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
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	
}
