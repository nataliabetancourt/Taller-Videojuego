import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class PlayScreen {
	
	private PApplet app;
	private PFont font;
	private PImage background, bar, life, greenAlien;
	private Spaceship ship;
	private ArrayList<Enemy> aliensGreen;
	private int frames, time, minutes, seconds, points, lifeShip, hearts;
	private boolean damage;
	
	public PlayScreen(PApplet app) {
		this.app = app;
		this.frames = 40;
		this.time = 0;
		this.minutes = 0;
		this.seconds = 0;
		this.points = 0;
		this.lifeShip = 0;
		this.hearts = 5;
		this.damage = false;
		
		//Class and ArrayLists
		ship = new Spaceship(app);
		aliensGreen = new ArrayList<>();
		
		//Images
		background = app.loadImage ("./data/playscreen.jpg");		bar = app.loadImage("./data/bar.png");
		life = app.loadImage("./data/heart.png");							greenAlien = app.loadImage("./data/Green alien.png");
		
		//Font
		font = app.createFont("Orbitron-Medium.ttf", 20);
	}
	
	public void draw() {
		//Elements for the screen
		//Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		//Bar
		app.image(bar, 0, 635, 1200, 60);
		app.textFont(font);
		app.text("Points: " + points, 560, 670);
		playTime();
		shipLife();
		
		//Class elements
		ship.draw();
		ship.shoot();
		addGreenAliens();
		drawGreenAliens();
		greenAliensImpacts();
	}
	
	private void playTime() {
		time++;
		if (time == 20) {
			seconds++;
			time = 0;
		}
		
		if (seconds == 60) {
			seconds = 0;
			minutes++;
		}

		app.fill(255);
		app.textFont(font);
		if (seconds<10 && minutes<10) {
			app.text("Tiempo: " + "0" + minutes + ":" + "0" + seconds, 1000, 670);
		}  else if (seconds > 10 && minutes < 10) {
			app.text("Tiempo: " + "0" + minutes + ":" + seconds, 1000, 670);
		}

	}
	
	private void shipLife() {
		int xLife = 50;
		int yLife = 665;
		
		for (int i = 0; i < hearts; i++) {
			app.imageMode(PConstants.CENTER);
			app.image(life, xLife + (i*50), yLife, 40, 40);
			
			if (lifeShip == 6) {
					lifeShip = 0;
					hearts -= 1;
				}
				
			if (hearts == 0) {
					//Gameover
				}
			
		}
	}

	private void addGreenAliens() {
		app.frameRate(frames);
		int xTemp = 0;
		int yTemp = 115;
		if (app.frameCount == frames) {
			app.frameCount = 0;
			aliensGreen.add(new EnemyBasic(app, xTemp, yTemp));
		}
		
		if (seconds == 30) {
			frames = 30;
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
				lifeShip += aliensGreen.get(i).getDamage();
				aliensGreen.remove(i);
			}
			
			//Enemy and Ship
			double distanceEandS =distanceBetweenPoints(aliensGreen.get(i).getX(), ship.getX(), aliensGreen.get(i).getY(), ship.getX());
			if (distanceEandS < ship.getX()) {
				lifeShip += aliensGreen.get(i).getDamage();
			}
			
			//Enemies and bullets
			for (int j = 0; j < ship.getBullets().size(); j++) {
				double distanceBandE = distanceBetweenPoints(aliensGreen.get(i).getX(), ship.getBullets().get(j).getX(), aliensGreen.get(i).getY(), ship.getBullets().get(j).getY());
				if (ship.getBullets().get(j).isVisible() && distanceBandE < aliensGreen.get(i).getWidth()/2) {
					aliensGreen.get(i).setLife(aliensGreen.get(i).getLife()-ship.getBullets().get(j).getDamage());
					ship.getBullets().get(j).setVisible(false);
					ship.getBullets().get(j).setDamage(0);
					points += aliensGreen.get(i).getPoints();
				}
				
				if (aliensGreen.get(i).getLife() == 0) {	
					aliensGreen.remove(i);
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
