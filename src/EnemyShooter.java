import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class EnemyShooter extends Enemy{

	private ArrayList <Bullet> meteors;
	private PImage meteor;
	
	public EnemyShooter(PApplet app, int x, int y) {
		super(app, x, y);
		this.points = 20;

		//ArrayList
		meteors = new ArrayList<>();
		
		//Images
		meteor = app.loadImage("./data/Meteor.png");
		
	}
	
	@Override
	public void draw(PImage img) {
		super.draw(img);
		generateBullets();
		shoot();
		eliminateBullet();
	}
	
	public void generateBullets() {
		if (app.frameCount%80 == 0) {
			Bullet bullet = new Bullet(app, x, y);
			meteors.add(bullet);
		}

	}
	
	public void shoot() {
		for (int i = 0; i < meteors.size(); i++) {
			meteors.get(i).draw(meteor);
			meteors.get(i).moveEnemy();
		}
	}
	
	public void eliminateBullet() {
		for (int i = 0; i <meteors.size(); i++) {
			if (meteors.get(i).getY() > 700) {
				meteors.remove(i);
			}
		}
	}

	public ArrayList<Bullet> getMeteors() {
		return meteors;
	}
	
	
}
