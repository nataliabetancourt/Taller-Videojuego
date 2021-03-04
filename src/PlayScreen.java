import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class PlayScreen {
	
	private PApplet app;
	private PImage background, bar, life;
	private Spaceship ship;
	
	public PlayScreen(PApplet app) {
		this.app = app;
		
		//Class
		ship = new Spaceship(app);
		
		//Images
		background = app.loadImage ("./data/playscreen.jpg");		bar = app.loadImage("./data/bar.png");
		life = app.loadImage("./data/heart.png");
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
	}
	
	public Spaceship getShip() {
		return ship;
	}

}
