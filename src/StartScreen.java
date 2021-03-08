import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class StartScreen {
	
	private PApplet app;
	private PImage background, start;
	private int xButton, yStart;
	private int screen;

	public StartScreen(PApplet app) {
		this.app = app;
		//Coordinations of buttons
		this.xButton = 600;
		this.yStart = 580;
		this.screen = 0;
		
		//Images
		background = app.loadImage("./data/startscreen.jpg");			start = app.loadImage("./data/startbutton.png");
		
	}
	
	public void draw() {
		//Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		//Start Button
		app.imageMode(PConstants.CENTER);
		app.image(start, xButton, yStart, 200, 40);
		
	}

	public void ClickButtons() {
		//Start Button
		if (app.mouseX > xButton-100 && app.mouseX < xButton+100 && app.mouseY > yStart-20 && app.mouseY < yStart+20) {
			screen = 1;
		}	
	}
	
	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}
}
