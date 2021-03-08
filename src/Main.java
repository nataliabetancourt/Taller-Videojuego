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
	InstructionScreen instructions;
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
		instructions = new InstructionScreen(this);
		
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
		//Start Screen
		case 0:
			start.draw();
			break;
		//Instructions
		case 1:
			instructions.draw();
			break;
		//Play Screen
		case 2:
			playScreen.draw();
			keyMovements();
			break;
		//Gameover screen
		case 3:
			gameover.draw();
			break;
		}
		
		//Set screen from classes
		
		//Instructions
		if (start.getScreen() == 1) {
			screen = 1;
		}
		
		//Play
		if (instructions.getScreen() == 2) {
			screen = 2;
		}
		
		//Gameover screen
		if (playScreen.isGameover() == true) {
			screen = 3;
		}
		
		//Play screen
		if (gameover.getScreen() == 2) {
			screen = 2;
		}
	
	}

	@Override
	public void mouseClicked() {
		start.ClickButtons();
		gameover.ClickButton();
		instructions.ClickButtons();

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
