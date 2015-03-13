package pewmode;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Star {
	private Image image;
	float xPos;
	float yPos;
	float speed;
	float scale;
	static List<Star> stars = new ArrayList<>();
		
	public Star(Image star1) {
		reset(); 
		this.xPos = -10 + (float) (Math.random()*2010);
		image = star1;
	}

	private void reset() {
		this.yPos = (float) (Math.random()*1080);
		xPos = 2000;
		speed = 2 + (float) (Math.random())*2;
		scale = speed/4;
	}
	
	public void draw() {
		image.draw(xPos, yPos, scale);
	}
	
	public void update() {
		xPos -= speed;
		if(xPos < -10)
			reset();
		if(yPos < -10)
			yPos = 1080;
		if(yPos > 1090)
			yPos = 0;
	}
	
	public static void initStars(int nbrOfStars) throws SlickException {
		Image star1 = new Image("star1.png");
		Image star2 = new Image("star2.png");
		
		for (int n = 0; n < nbrOfStars/2; n++)
			stars.add(new Star(star1));
		for (int n = 0; n < nbrOfStars/2; n++)
			stars.add(new Star(star2));
	}

	public static List<Star> getStars() {
		return stars;
	}

	public static void renderStars() {
		for(Star s : stars) {
			s.draw();
		}
	}
	
	public static void updateStars() {
		for(Star s : stars) {
			s.update();
		}	
	}

	public static void move(double x, double y) {
		for(Star s : stars) {
			s.xPos+=s.speed*x;
			s.yPos+=s.speed*y;
		}
	}
}
