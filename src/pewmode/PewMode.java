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

public class PewMode extends BasicGame {
    public float fade;
    public int countdown, timer;
    Image pewmodetitle, pressanykey, flame, flameAB;
    static final int screenHeight = 1200;
    Sprites sprites;
    boolean menumode = true;
    float scalemodifier = 1;
    Input in = new Input(screenHeight);
    int shipMaxSpeed = 8;
    private long lastShotFired;

    public PewMode(String gamename) {
        super(gamename);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (menumode) {
            menumode = false;
            sprites.ship.setAlpha(1);
            sprites.ship.setScale(0.3f);
            sprites.ship.setxPos(200);
            sprites.ship.setyPos(400);
            super.keyPressed(key, c);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        Mouse.setGrabbed(true);
        sprites = new Sprites(shipMaxSpeed);
        sprites.init();

        countdown = 30;
        fade = 0;
        pewmodetitle = new Image("title.png");
        pressanykey = new Image("anykey.png");
        flameAB = new Image("flame.png");
        flameAB.setRotation(90);
        flame = new Image("flame.png");
        flame.setAlpha(0);
        pewmodetitle.setAlpha(0);
        pressanykey.setAlpha(0);
        timer = 0;
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (menumode)
            introAndMenu();
        else
            updateGame();
    }

    private void updateGame() throws SlickException {
        sprites.update();

        scalemodifier = (float) (1 + Math.sin(System.nanoTime()) / 10);

        if (flame.getAlpha() > 0f)
            setFlameAlpha(0f);
        else
            setFlameAlpha(0.8f);

        updateMovement();
    }

    private void updateMovement() throws SlickException {
        if (in.isKeyDown(Input.KEY_S)) {
            sprites.down();
        }
        if (in.isKeyDown(Input.KEY_W)) {
            sprites.up();
        }
        if (in.isKeyDown(Input.KEY_A)) {
            sprites.left();
        }
        if (in.isKeyDown(Input.KEY_D)) {
            sprites.right();
        }
        if (in.isKeyDown(Input.KEY_SPACE)) {
            if (System.currentTimeMillis() > lastShotFired + 50) {
                sprites.fires.fire(new Fire(sprites.ship.getxPos(), sprites.ship.getyPos()));
                lastShotFired = System.currentTimeMillis();
            }
        }
    }

    private void setFlameAlpha(float a) {
        flame.setAlpha(a);
        flameAB.setAlpha(a);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (menumode)
            renderMenu();
        else
            renderGame();
    }

    private void renderGame() {
        sprites.render();

        renderFlameGroup(190);  // The flames not below the ship?
        renderFlameGroup(300);  // The flames below the ship
        renderAfterBurner();
    }

    private void renderMenu() {
        sprites.render();

        renderFlameGroup(247);
        renderFlameGroup(357);
        pressanykey.draw(500, 500, 1);
        pewmodetitle.draw(60, 100, 4.3f);
    }

    private void renderAfterBurner() {
        flameAB.draw(sprites.ship.getxPos() - 150 * sprites.ship.getScale() - ((sprites.ship.getScale() * scalemodifier - 2) * 10),
                sprites.ship.getyPos() - 70 - ((sprites.ship.getScale() * scalemodifier - 2) * 60), sprites.ship.getScale() * 5 * scalemodifier);
    }

    private void renderFlameGroup(int offset) {
        renderFlame(offset);
        renderFlame(offset + 20);
        renderFlame(offset + 40);
    }

    private void renderFlame(float xOffset) {
        flame.draw(sprites.ship.getxPos() + xOffset * sprites.ship.getScale() - ((sprites.ship.getScale() * scalemodifier - 2) * 10),
                sprites.ship.getyPos() + 250 * sprites.ship.getScale(), sprites.ship.getScale() * scalemodifier);
    }

    private void introAndMenu() {
        sprites.update();

        scalemodifier = (float) (1 + Math.sin(System.nanoTime()) / 10);

        sprites.ship.setAlpha(sprites.ship.getAlpha() + 0.004f);

        if (sprites.ship.getAlpha() < 1f) {
            sprites.ship.setScale(sprites.ship.getxPos() * sprites.ship.getyPos() / 60000.00f);
            sprites.ship.blindMove(2, 0);

        } else {
            if (flame.getAlpha() > 0f)
                setFlameAlpha(0f);
            else
                setFlameAlpha(0.8f);
        }

        if (570 + sprites.ship.getyPos() < screenHeight) {
            sprites.ship.blindMove(0, 0.9f);
        } else {
            pewmodetitle.setAlpha((pewmodetitle.getAlpha() + 0.05f));
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
            appgc = new AppGameContainer(new PewMode("PewMode!"));
            appgc.setDisplayMode(1920, screenHeight, false);
            appgc.setFullscreen(true);
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(PewMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}