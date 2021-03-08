import processing.core.PApplet;

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
	GameoverScreen gameover;
	//Int
	int screen;
	//Boolean
	boolean left, right, shoot;
	
	@Override
	public void setup() {
		//Class
		start = new StartScreen(this);
		playScreen = new PlayScreen(this);
		gameover = new GameoverScreen(this);
		
		//Int
		screen = 3;
		
		//Boolean
		left = false;					right = false;
		shoot = false;
		
	}
	
	@Override
	public void draw() {
		//Screens
		switch (screen) {
		//Start Screen
		case 0:
			start.draw();
			break;
		//Play Screen
		case 1:
			playScreen.draw();
			keyMovements();
			break;
		//Gameover screen
		case 3:
			gameover.draw();
			break;
		}
		
		//Set screen from classes
		if (start.getScreen() == 1) {
			screen = 1;
		}
		
		if (playScreen.isGameover() == true) {
			screen = 3;
		}
		
		if (gameover.getScreen() == 1) {
			screen = 1;
		}
	}

	@Override
	public void mouseClicked() {
		start.ClickButtons();
		gameover.ClickButton();

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
