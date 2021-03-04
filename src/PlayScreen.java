import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class PlayScreen {
	
	private PApplet app;
	private PImage background, bar, life, greenAlien;
	private Spaceship ship;
	private ArrayList<Enemy> aliensGreen;
	private int frames;
	
	public PlayScreen(PApplet app) {
		this.app = app;
		this.frames = 40;
		
		//Class and ArrayLists
		ship = new Spaceship(app);
		aliensGreen = new ArrayList<>();
		
		//Images
		background = app.loadImage ("./data/playscreen.jpg");		bar = app.loadImage("./data/bar.png");
		life = app.loadImage("./data/heart.png");							greenAlien = app.loadImage("./data/Green alien.png");
	}
	
	public void draw() {
		//Elements for the screen
		//Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		//Bar
		app.image(bar, 0, 635, 1200, 60);
		
		//Class elements
		ship.draw();
		ship.shoot();
		addGreenAliens();
		drawGreenAliens();
		greenAliensImpacts();
	}
	
	private void addGreenAliens() {
		app.frameRate(frames);
		int xTemp = 0;
		int yTemp = 115;
		if (app.frameCount == frames) {
			app.frameCount = 0;
			aliensGreen.add(new EnemyBasic(app, xTemp, yTemp));
		}
	}
	
	private void drawGreenAliens() {
		for (int i = 0; i < aliensGreen.size(); i++) {
			aliensGreen.get(i).draw(greenAlien);
			aliensGreen.get(i).moveEnemy();
		}
	}
	
	private void greenAliensImpacts() {
		for (int i = 0; i < aliensGreen.size(); i++) {
			//Enemy and border
			if (aliensGreen.get(i).getY() > 700) {
				ship.setLife(ship.getLife() - aliensGreen.get(i).getDamage());
				aliensGreen.remove(i);
			}
			
			//Enemy and Ship
			double distanceEandS =distanceBetweenPoints(aliensGreen.get(i).getX(), ship.getX(), aliensGreen.get(i).getY(), ship.getX());
			if (distanceEandS < ship.getX()) {
				ship.setLife(ship.getLife() - aliensGreen.get(i).getDamage());
			}
			
			//Enemies and bullets
			for (int j = 0; j < ship.getBullets().size(); j++) {
				double distanceBandE = distanceBetweenPoints(aliensGreen.get(i).getX(), ship.getBullets().get(j).getX(), aliensGreen.get(i).getY(), ship.getBullets().get(j).getY());
				if (ship.getBullets().get(j).isVisible() && distanceBandE < aliensGreen.get(i).getWidth()/2) {
					aliensGreen.get(i).setLife(aliensGreen.get(i).getLife()-ship.getBullets().get(j).getDamage());
					ship.getBullets().get(j).setVisible(false);
					ship.getBullets().get(j).setDamage(0);
				}
				
				if (aliensGreen.get(i).getLife() == 0) {
				
				}
			}
		}
	}
	
	private double distanceBetweenPoints(double x1, double x2, double y1, double y2) {
		return Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1));
	}
	
	public Spaceship getShip() {
		return ship;
	}

}
