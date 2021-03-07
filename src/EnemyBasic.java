import processing.core.PApplet;
import processing.core.PImage;

public class EnemyBasic  extends Enemy{

	private int bounce;

	public EnemyBasic(PApplet app, int x, int y) {
		super(app, x, y);
		this.bounce = 0;
		this.life = 4;
	}

	@Override
	public void draw(PImage img) {
		super.draw(img);
	}
	
	@Override
	public void moveEnemy() {
		switch (bounce) {
		case 0:
			x += speed;
			if (x > 1200) {
				y += 90;
				bounce = 1;
			}
			break;
		case 1:
			x -= speed;
			if (x < 20) {
				y += 90;
				bounce = 0;
			}
			break;
		}
	}
}
