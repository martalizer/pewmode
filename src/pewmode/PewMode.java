package pewmode;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

public class PewMode extends BasicGame {
    public float fade;
    public int countdown, timer;
    Image pewmodetitle, pressanykey, flame, flameAB;
    static final int screenHeight = 1200;
    Sprites sprites;
    boolean menumode = true;
    float flameScale = 1;
    Input in = new Input(screenHeight);
    int shipMaxSpeed = 8;

    public PewMode(String gamename) {
        super(gamename);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (menumode) {
            menumode = false;
            sprites.getShip().setAlpha(1);
            sprites.getShip().setScale(0.3f);
            sprites.getShip().setxPos(200);
            sprites.getShip().setyPos(400);
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
        flameScale = (float) (1 + Math.sin(System.nanoTime()) / 10);

        if (menumode)
            updateIntroAndMenu();
        else
            updateGame();
    }

    private void updateGame() throws SlickException {
        sprites.update();

        toggleFlameAlpha();
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
            sprites.fireWeapon();
        }
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

        renderFlameGroup(190);
        renderFlameGroup(300);
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
        float scale = sprites.getShip().getScale();
        float xPos = sprites.getShip().getxPos();
        float yPos = sprites.getShip().getyPos();

        flameAB.draw(xPos - 150 * scale - ((scale * flameScale - 2) * 10),
                yPos - 70 - ((scale * flameScale - 2) * 60), scale * 5 * flameScale);
    }

    private void renderFlameGroup(int offset) {
        renderFlame(offset);
        renderFlame(offset + 20);
        renderFlame(offset + 40);
    }

    private void renderFlame(float xOffset) {
        float scale = sprites.getShip().getScale();
        float xPos = sprites.getShip().getxPos();
        float yPos = sprites.getShip().getyPos();

        flame.draw(xPos + xOffset * scale - ((scale * flameScale - 2) * 10),
                yPos + 250 * scale, scale * flameScale);
    }

    private void updateIntroAndMenu() {
        sprites.update();

        sprites.getShip().setAlpha(sprites.getShip().getAlpha() + 0.004f);

        if (sprites.getShip().getAlpha() < 1f) {
            sprites.getShip().setScale(sprites.getShip().getxPos() * sprites.getShip().getyPos() / 60000.00f);
            sprites.getShip().blindMove(2, 0);

        } else {
            toggleFlameAlpha(); // Will remain 0 until ship is fully visible
        }

        if (570 + sprites.getShip().getyPos() < screenHeight) {
            sprites.getShip().blindMove(0, 0.9f);
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

    private void toggleFlameAlpha() {
        if (flame.getAlpha() > 0f)
            setFlameAlpha(0f);
        else
            setFlameAlpha(0.8f);
    }

    private void setFlameAlpha(float a) {
        flame.setAlpha(a);
        flameAB.setAlpha(a);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            PewMode pm = new PewMode("PewMode!");
            ScalableGame sg = new ScalableGame(pm, 1920, 1200, true);

            appgc = new AppGameContainer(sg);
            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);

            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setVSync(true);
            appgc.start();

        } catch (SlickException ex) {
            Logger.getLogger(PewMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}