import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class PlayScreen {
	
	private PApplet app;
	private PFont font;
	private PImage background, bar, life, greenAlien, pinkAlien;
	private Spaceship ship;
	private ArrayList<Enemy> aliensGreen;
	private ArrayList<EnemyShooter> aliensPink;
	private int time, minutes, seconds, points, lifeShip, hearts, time1, time2, vulnerable;
	private boolean gameover;
	
	public PlayScreen(PApplet app) {
		this.app = app;
		this.time = 0;
		this.minutes = 0;
		this.seconds = 0;
		this.points = 0;
		this.lifeShip = 0;
		this.hearts = 5;
		this.time1 = 0;
		this.time2 = 0;
		this.vulnerable = 0;
		this.gameover = false;
		
		//Class and ArrayLists
		ship = new Spaceship(app);
		aliensGreen = new ArrayList<>();
		aliensPink = new ArrayList<>();
		
		//Images
		background = app.loadImage ("./data/playscreen.jpg");		bar = app.loadImage("./data/bar.png");
		life = app.loadImage("./data/heart.png");							greenAlien = app.loadImage("./data/Green alien.png");
		pinkAlien = app.loadImage("./data/Pink Alien.png");
		
		//Font
		font = app.createFont("Orbitron-Medium.ttf", 20);				
	}
	
	public void draw() {
		//Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		
		//Class elements
		ship.draw();
		ship.shoot();
		ship.eliminateBullet();
		addAliens();
		drawGreenAliens();
		greenAliensImpacts();
		drawPinkAliens();
		pinkAliensImpacts();
		
		//Elements for the screen
		//Bar
		app.image(bar, 0, 635, 1200, 60);
		//Points, time and life
		app.textFont(font);
		app.text("Points: " + points, 560, 670);
		playTime();
		shipLife();
		
		//Countdown for damage on ship
		if (vulnerable > 0) {
			vulnerable--;
		}	
		
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
		}  else if (seconds >= 10 && minutes < 10) {
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
					gameover = true;
				}
		}
	}
	
	private void addAliens() {
		app.frameRate(60);
		int xTemp1 = 0;
		int yTemp1 = 145;
		int xTemp2 = (int) (60 + Math.random() * 1100);
		int yTemp2 = 60;
		
		if (app.frameCount%32 == 0) {
			aliensGreen.add(new EnemyBasic(app, xTemp1, yTemp1));
		}
		
		if (app.frameCount%200 == 0 && seconds >= 30) {
			aliensPink.add(new EnemyShooter(app, xTemp2, yTemp2));
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
			//Difficulty
			if (minutes >= 1) {
				aliensGreen.get(i).setSpeed(6);
			}
			
			if (minutes >= 1 && seconds >= 30) {
				aliensGreen.get(i).setSpeed(8);
			}
			
			if (minutes >= 2) {
				aliensGreen.get(i).setSpeed(10);
			}
			
			//Enemy and border
			if (aliensGreen.get(i).getY() > 700) {
				gameover = true;
				aliensGreen.remove(i);
			}
			
			//Enemy and Ship
			double distanceEandS =distanceBetweenPoints(aliensGreen.get(i).getX(), ship.getX(), aliensGreen.get(i).getY(), ship.getX());
			if (distanceEandS < ship.getX()/2) {
				if (vulnerable == 0) {
					lifeShip += aliensGreen.get(i).getDamage();
				}
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
					points += aliensGreen.get(i).getPoints();
					aliensGreen.remove(i);
				}
			}
		}
	}
	
	private void drawPinkAliens() {
		for (int i = 0; i < aliensPink.size(); i++) {
			aliensPink.get(i).draw(pinkAlien);
		}
	}
	
	private void pinkAliensImpacts() {
		for (int i = 0; i < aliensPink.size(); i++) {
			//Enemy bullets against ship
			for (int j = 0; j < aliensPink.get(i).getMeteors().size(); j++) {
				double distanceMandS = distanceBetweenPoints(ship.getX(), aliensPink.get(i).getMeteors().get(j).getX(), 
						ship.getY(),  aliensPink.get(i).getMeteors().get(j).getY());
				if (distanceMandS < ship.getWidth()/2 && aliensPink.get(i).getMeteors().get(j).isVisible()) {
					if (vulnerable == 0) {
						lifeShip += aliensGreen.get(i).getDamage();
					}
					aliensPink.get(i).getMeteors().get(j).setVisible(false);
				}
			}
			
			//Ship bullets against pink aliens
			for (int j = 0; j < ship.getBullets().size(); j++) {
				double distanceBandA = distanceBetweenPoints(aliensPink.get(i).getX(), ship.getBullets().get(j).getX(), 
						aliensPink.get(i).getY(), ship.getBullets().get(j).getY());
				if (ship.getBullets().get(j).isVisible() && distanceBandA < aliensPink.get(i).getWidth()/2) {
					aliensPink.get(i).setLife(aliensPink.get(i).getLife()-ship.getBullets().get(j).getDamage());
					ship.getBullets().get(j).setVisible(false);
					ship.getBullets().get(j).setDamage(0);
					points += aliensPink.get(i).getPoints();
					
					if (aliensPink.get(i).getLife() == 0) {	
						aliensPink.remove(i);
					}
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
	
	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getPoints() {
		return points;
	}
	
	public boolean isGameover() {
		return gameover;
	}


}
