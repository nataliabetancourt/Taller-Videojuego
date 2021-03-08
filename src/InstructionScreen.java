import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class InstructionScreen {

	private PApplet app;
	private PImage background, start;
	private int xButton, yButton;
	private int screen;
	
	public InstructionScreen(PApplet app) {
		this.app = app;
		this.xButton = 1050;
		this.yButton = 640;
		this.screen = 0;
		
		//Images
		background =  app.loadImage("./data/Instrucciones.jpg");		start = app.loadImage("./data/startbutton.png");
		
	}
	
	public void draw() {
		//Background
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		//Start button
		app.imageMode(PConstants.CENTER);
		app.image(start, xButton, yButton, 200, 40);
	}

	public void ClickButtons() {
		//Start Button
		if (app.mouseX > xButton-100 && app.mouseX < xButton+100 && app.mouseY > yButton-20 && app.mouseY < yButton+20) {
			screen = 2;
		}	
	}
	
	public int getScreen() {
		return screen;
	}

}
