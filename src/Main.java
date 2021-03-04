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
	boolean left, right, shoot;
	
	@Override
	public void setup() {
		//Class
		start = new StartScreen(this);
		playScreen = new PlayScreen(this);
		
		//Int
		screen = 0;
		
		//Boolean
		left = false;					right = false;
		shoot = false;
		
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
			keyMovements();
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
		switch (key) {
		case 'a':
			left = true;
			break;
		case 'd':
			right = true;
			break;
		case ' ':
			shoot = true;
			break;
		}
	}
	
	@Override
	public void keyReleased() {
		switch (key) {
		case 'a':
			left = false;
			break;
		case 'd':
			right = false;
			break;
		}
	}
	
	private void keyMovements() {
		if (left == true) {
			playScreen.getShip().moveShipLeft();
		}
		
		if (right == true) {
			playScreen.getShip().moveShipRight();
		}

		if (shoot == true) {
			playScreen.getShip().generateBullets();
			shoot = false;
		}
	}
	
}
