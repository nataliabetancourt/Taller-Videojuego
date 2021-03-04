import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Spaceship {
	
	private PApplet app;
	private PImage ship;
	private int x, y, life, dir;
	
	public Spaceship(PApplet app) {
		this.app = app;
		this.x = 600;
		this.y = 570;
		this.life = 25;
		//Dir is to indicate in which direction the ship is going, 0 = still, 1 = left and 2 = right
		this.dir = 0; 
		
		//Images
		ship = app.loadImage("./data/Spaceship.png");
		
	}
	
	public void draw() {
		app.imageMode(PConstants.CENTER);
		app.image(ship, x, y, 70, 130);
	}
	
	public void moveShip(int key) {
		switch (key) {
		case 'a':
			x-=5;
			break;
		case 'd':
			x+=5;
			break;
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

	public void setDir(int dir) {
		this.dir = dir;
	}

}
