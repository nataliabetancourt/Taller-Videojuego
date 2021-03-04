import processing.core.PApplet;
import processing.core.PConstants;

public class Main extends PApplet{


	
	public static void main(String[] args) {
		PApplet.main("Main");
	}
		
	
	@Override
	public void settings() {
		size(1200, 700);
	}
	
	//Class
	StartScreen start;
	PlayScreen playScreen;
	//Int
	int screen;
	//Boolean
	boolean left, right;
	
	@Override
	public void setup() {
		//Class
		start = new StartScreen(this);
		playScreen = new PlayScreen(this);
		
		//Int
		screen = 0;
		
		//Boolean
		left = false;					right = false;
		
	}
	
	@Override
	public void draw() {
		//Screens
		switch (screen) {
		case 0:
			start.draw();
			break;
		case 1:
			playScreen.draw();
			break;
		}
		
		//Set screen from classes
		if (start.getScreen() == 1) {
			screen = 1;
		}
		
	}

	@Override
	public void mouseClicked() {
		start.ClickButtons();
	}
	
	@Override
	public void keyPressed() {
		playScreen.getShip().moveShip(key);
		
	}
	
}
