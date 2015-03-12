package pewmode;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame {
	public float blahx, blahy, scale, fade;
	public int countdown, timer;
	Image superStarshipDeluxe, pewmodetitle, pressanykey, flame, star1, star2, flameAB;
	boolean menumode = true;
	float scalemodifier = 1;
	Input in = new Input(1080);
	int shipMaxSpeed = 8;
	
	public SimpleSlickGame(String gamename)	{
		super(gamename);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		menumode = false;
		super.keyPressed(key, c);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		myInit();
		Star.initStars(400);
		Mouse.setGrabbed(true);
	}

	private void myInit() throws SlickException {
		blahx = 10;
		blahy = 10;
		countdown = 30;
		fade = 0;
		superStarshipDeluxe = new Image("ship.png");
		pewmodetitle = new Image("title.png");
		pressanykey = new Image("anykey.png");
		flameAB = new Image("flame.png");
		flameAB.setRotation(90);
		flame = new Image("flame.png");
		superStarshipDeluxe.setAlpha(0);
		flame.setAlpha(0);
		pewmodetitle.setAlpha(0);
		pressanykey.setAlpha(0);
		timer = 0;
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(menumode)
			introAndMenu();
		else
			updateGame();
	}

	private void updateGame() {
		Star.updateStars();
		scalemodifier = (float) (1+Math.sin(System.nanoTime())/10);
		
		if(flame.getAlpha() == 0.8f)
			setFlameAlpha(0f);
		else
			setFlameAlpha(0.8f);
		
		updateMovement();
	}

	private void updateMovement() {
		// down
		if (in.isKeyDown(Input.KEY_S)) {
			this.blahy += shipMaxSpeed;
			Star.move(0, -0.5);
		}
		// up
		if (in.isKeyDown(Input.KEY_W)) {
			this.blahy -= shipMaxSpeed;
			Star.move(0, 0.5);
		}
		// left
		if (in.isKeyDown(Input.KEY_A)) {
			this.blahx -= shipMaxSpeed;
			Star.move(0.5, 0);
		}
		// right
		if (in.isKeyDown(Input.KEY_D)) {
			this.blahx += shipMaxSpeed;
			Star.move(-0.5, 0);
		}		
	}

	private void setFlameAlpha(float a) {
		flame.setAlpha(a);
		flameAB.setAlpha(a);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(menumode)
			renderMenu();
		else
			renderGame();
	}

	private void renderGame() {
		superStarshipDeluxe.setAlpha(1);
		scale = 0.3f;
		Star.renderStars();
		
		superStarshipDeluxe.draw(blahx, blahy, scale);
				
		renderFlameGroup(190); renderFlameGroup(300);  // The flames below the ship
		renderAfterBurner();
	}

	private void renderAfterBurner() {
		flameAB.draw(blahx-150*scale-((scale*scalemodifier-2)*10), blahy-70-((scale*scalemodifier-2)*60), scale*5*scalemodifier);
	}

	private void renderMenu() {
		Star.renderStars();
		superStarshipDeluxe.draw(blahx, blahy, scale);
		renderFlameGroup(247);
		renderFlameGroup(357);
		pressanykey.draw(500, 500, 1);
		pewmodetitle.draw(60, 100, 4.3f);
	}

	private void renderFlameGroup(int offset) {
		renderFlame(offset);
		renderFlame(offset+20);
		renderFlame(offset+40);
	}

	private void renderFlame(float xOffset) {
		flame.draw(blahx+xOffset*scale-((scale*scalemodifier-2)*10), blahy+250*scale, scale*scalemodifier);
	}

	private void introAndMenu() {
		Star.updateStars();
		
		scalemodifier = (float) (1+Math.sin(System.nanoTime())/10);
		
		superStarshipDeluxe.setAlpha((float) (superStarshipDeluxe.getAlpha()+0.004));
		
		if(superStarshipDeluxe.getAlpha() < 1f) {
			scale = (float) blahx*blahy/60000.00f;
			blahx+=2;
		} 
		else {
			if(flame.getAlpha() == 0.8f)
				setFlameAlpha(0f);
			else
				setFlameAlpha(0.8f);				
		}	
		
		if(570 + blahy < 1080) {
			blahy += 0.9f;
		}
		else {
			pewmodetitle.setAlpha((pewmodetitle.getAlpha()+0.05f));
		}

		if (pewmodetitle.getAlpha() > 1) {
			if (countdown > 0)
				countdown--;
			else {
				float sinblinkthing = Math.abs((float) Math.sin(System.nanoTime() / (10e7) * 0.7));
				pressanykey.setAlpha(sinblinkthing * fade);
				if (fade < 1)
					fade += 0.1f;
			}
		}
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("PewMode!"));
			appgc.setDisplayMode(1920, 1080, false);
			appgc.setFullscreen(false);
			appgc.setShowFPS(false);
			appgc.setVSync(true);
			appgc.start();
		}
		catch (SlickException ex) {
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}