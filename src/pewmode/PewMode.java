package pewmode;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

public class PewMode extends BasicGame {
    static final int SCREEN_HEIGHT = 1200,
                     SHIP_MAX_SPEED = 12;

    int countdown = 30;
    public float _fade = 0;
    Image pewmodetitle, pressanykey;
    Sprites sprites;
    boolean menumode = true;

    public PewMode(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        Mouse.setGrabbed(true);
        sprites = new Sprites(SHIP_MAX_SPEED, SCREEN_HEIGHT);
        sprites.init();
        pewmodetitle = new Image("title.png");
        pewmodetitle.setAlpha(0);
        pressanykey = new Image("anykey.png");
        pressanykey.setAlpha(0);
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
    public void update(GameContainer gc, int i) throws SlickException {
        sprites.update();

        if (menumode)
            updateIntroAndMenu();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (menumode) {
            sprites.get(3).menu(true);     //enable rear flames
        }
        else {
            sprites.get(3).menu(false);    //disable rear flames
        }
        sprites.render();

        if(menumode) {
            pressanykey.draw(500, 500, 1);
            pewmodetitle.draw(60, 100, 4.3f);
        }
    }

    private void updateIntroAndMenu() {
        sprites.getShip().setAlpha(sprites.getShip().getAlpha() + 0.004f);

        if (sprites.getShip().getAlpha() < 1f) {
            sprites.getShip().setScale(sprites.getShip().getxPos() * sprites.getShip().getyPos() / 60000.00f);
            sprites.getShip().blindMove(2, 0);

        }
        if (570 + sprites.getShip().getyPos() < SCREEN_HEIGHT) {
            sprites.getShip().blindMove(0, 0.9f);
        } else {
            pewmodetitle.setAlpha((pewmodetitle.getAlpha() + 0.05f));
        }
        if (pewmodetitle.getAlpha() > 1) {
            if (countdown > 0)
                countdown--;
            else {
                float sinblinkthing = Math.abs((float) Math.sin(System.nanoTime() / (10e7) * 0.7));
                pressanykey.setAlpha(sinblinkthing * _fade);
                if (_fade < 1)
                    _fade += 0.1f;
            }
        }
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            PewMode pm = new PewMode("PewMode!");
            ScalableGame sg = new ScalableGame(pm, 1920, 1200, true);
            appgc = new AppGameContainer(sg);
            //appgc.setDisplayMode(2880, 1800, true);
            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException ex) {throw new RuntimeException(ex);}
    }
}