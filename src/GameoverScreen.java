import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class GameoverScreen {
	
	private PApplet app;
	private PImage background, restart;
	private PFont font;
	private int xButton, yButton;
	private int screen, seconds, minutes, points;
	
	public GameoverScreen(PApplet app) {
		this.app = app;
		this.xButton = 600;
		this.yButton = 600;
		this.screen = 0;
		this.seconds = 0;
		this.minutes = 0;
		this.points = 0;
		
		//Images
		background = app.loadImage("./data/Gameover.jpg");			restart = app.loadImage("./data/Restart Button.png");
		
		//Font
		font = app.createFont("Orbitron-Medium.ttf", 36);
	}
	
	public void draw() {
		// Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		//Back button
		app.imageMode(PConstants.CENTER);
		app.image(restart, xButton, yButton, 200, 40);
		
		//Time
		app.fill(255);
		app.textFont(font);
		app.textAlign(PConstants.CENTER);
		if (seconds<10 && minutes<10) {
			app.text("Tiempo: " + "0" + minutes + ":" + "0" + seconds, 600, 370);
		}  else if (seconds >= 10 && minutes < 10) {
			app.text("Tiempo: " + "0" + minutes + ":" + seconds, 600, 380);
		}
		
		//Points
		app.textFont(font);
		app.text("Points: " +points, 600, 440);

	}
	
	public int getScreen() {
		return screen;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getxButton() {
		return xButton;
	}
	
	public int getyButton() {
		return yButton;
	}

}
