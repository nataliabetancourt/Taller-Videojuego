import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Enemy {
	
	protected PApplet app;
	protected int x, y, life, speed, width, height, damage;
	protected boolean visible;

	public Enemy(PApplet app, int x, int y) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.life = 4;
		this.speed = 10;
		this.width = 100;
		this.height = 85;
		this.visible = true;
		this.damage = 1;
	}

	public void draw(PImage img) {
		app.imageMode(PConstants.CENTER);
		app.image(img, x, y, width, height);
	}
	
	public void moveEnemy() {

	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getDamage() {
		return damage;
	}
}
