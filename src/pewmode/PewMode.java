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
    public float shipXpos, shipYpos, scale, fade;
    public int countdown, timer;
    Image superStarshipDeluxe, pewmodetitle, pressanykey, flame, flameAB;
    boolean menumode = true;
    float scalemodifier = 1;
    Input in = new Input(1080);
    int shipMaxSpeed = 8;
    private long lastShotFired;

    public PewMode(String gamename) {
        super(gamename);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (menumode) {
            menumode = false;
            superStarshipDeluxe.setAlpha(1);
            scale = 0.3f;
            shipXpos = 200;
            shipYpos = 400;
            super.keyPressed(key, c);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        myInit();
        Star.createStar(400);
        Mouse.setGrabbed(true);
    }

    private void myInit() throws SlickException {
        shipXpos = 10;
        shipYpos = 10;
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
        if (menumode)
            introAndMenu();
        else
            updateGame();
    }

    private void updateGame() throws SlickException {
        Star.updateStars();
        Fire.updateFires();
        scalemodifier = (float) (1 + Math.sin(System.nanoTime()) / 10);

        if (flame.getAlpha() > 0f)
            setFlameAlpha(0f);
        else
            setFlameAlpha(0.8f);
        updateMovement();
    }

    private void updateMovement() throws SlickException {
        if (in.isKeyDown(Input.KEY_S)) {
            down();
        }
        if (in.isKeyDown(Input.KEY_W)) {
            up();
        }
        if (in.isKeyDown(Input.KEY_A)) {
            left();
        }
        if (in.isKeyDown(Input.KEY_D)) {
            right();
        }
        if (in.isKeyDown(Input.KEY_SPACE)) {
            if (System.currentTimeMillis() > lastShotFired + 50) {
                Fire.initFire(shipXpos, shipYpos);
                lastShotFired = System.currentTimeMillis();
            }
        }
    }

    private void down() {
        this.shipYpos += shipMaxSpeed;
        Star.move(0, -0.5);
    }

    private void up() {
        this.shipYpos -= shipMaxSpeed;
        Star.move(0, 0.5);
    }

    private void left() {
        this.shipXpos -= shipMaxSpeed;
        Star.move(0.5, 0);
    }

    private void right() {
        this.shipXpos += shipMaxSpeed;
        Star.move(-0.5, 0);
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
        Star.renderStars();
        Fire.renderFire();

        superStarshipDeluxe.draw(shipXpos, shipYpos, scale);

        renderFlameGroup(190);
        renderFlameGroup(300);  // The flames below the ship
        renderAfterBurner();
    }

    private void renderMenu() {
        Star.renderStars();
        Fire.renderFire();
        superStarshipDeluxe.draw(shipXpos, shipYpos, scale);
        renderFlameGroup(247);
        renderFlameGroup(357);
        pressanykey.draw(500, 500, 1);
        pewmodetitle.draw(60, 100, 4.3f);
    }

    private void renderAfterBurner() {
        flameAB.draw(shipXpos - 150 * scale - ((scale * scalemodifier - 2) * 10),
                shipYpos - 70 - ((scale * scalemodifier - 2) * 60), scale * 5 * scalemodifier);
    }

    private void renderFlameGroup(int offset) {
        renderFlame(offset);
        renderFlame(offset + 20);
        renderFlame(offset + 40);
    }

    private void renderFlame(float xOffset) {
        flame.draw(shipXpos + xOffset * scale - ((scale * scalemodifier - 2) * 10),
                shipYpos + 250 * scale, scale * scalemodifier);
    }

    private void introAndMenu() {
        Star.updateStars();

        scalemodifier = (float) (1 + Math.sin(System.nanoTime()) / 10);

        superStarshipDeluxe.setAlpha((float) (superStarshipDeluxe.getAlpha() + 0.004));

        if (superStarshipDeluxe.getAlpha() < 1f) {
            scale = shipXpos * shipYpos / 60000.00f;
            shipXpos += 2;
        } else {
            if (flame.getAlpha() > 0f)
                setFlameAlpha(0f);
            else
                setFlameAlpha(0.8f);
        }

        if (570 + shipYpos < 1080) {
            shipYpos += 0.9f;
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
            appgc.setDisplayMode(1920, 1080, false);
            //appgc.setFullscreen(true);
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(PewMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}