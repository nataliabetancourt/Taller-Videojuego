import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class GameoverScreen {
	
	private PApplet app;
	private PImage background, restart;
	private PFont font;
	private int xButton, yButton;
	private int screen;
	private PlayScreen play;
	
	public GameoverScreen(PApplet app) {
		this.app = app;
		this.xButton = 600;
		this.yButton = 600;
		this.screen = 0;
		
		//Class
		play = new PlayScreen(app);
		
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
		if (play.getSeconds()<10 && play.getMinutes()<10) {
			app.text("Tiempo: " + "0" + play.getMinutes() + ":" + "0" + play.getSeconds(), 600, 380);
		}  else if (play.getSeconds() >= 10 && play.getMinutes() < 10) {
			app.text("Tiempo: " + "0" + play.getMinutes() + ":" + play.getSeconds(), 600, 380);
		}
		//Points
		app.textFont(font);
		app.text("Points: " + play.getPoints(), 600, 440);

	}
	
	public void ClickButton() {
		if (app.mouseX > xButton-100 && app.mouseX < xButton+100 && app.mouseY > yButton-20 && app.mouseY < yButton+20) {
			screen = 1;
		}
	}
	
	public int getScreen() {
		return screen;
	}

}
