package pewmode;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Sprites {
    public ArrayList<Spritable> sprites = new ArrayList<>();;
    public int shipMaxSpeed;
    private long lastShotFired = 0;
    int screenHeight;
    Input in = new Input(screenHeight);

    public Sprites(int maxspeed, int screenHeight) throws SlickException {
        shipMaxSpeed = maxspeed;
        this.screenHeight = screenHeight;

        sprites.add(new Stars(800, screenHeight));
        sprites.add(new Ship());
        sprites.add(new Fires());
        sprites.add(new Flames(getShip()));
    }

    public void update () {
        try {
            updateMovement();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        for (Spritable s : sprites) {
            s.update();
        }
    }

    public void init() throws SlickException {
        sprites.get(1).setxPos(10);
        sprites.get(1).setAlpha(0);
    }

    public void down() {
        sprites.get(1).move(0, shipMaxSpeed);
        sprites.get(0).move(0, -0.5f);
    }

    public void up() {
        sprites.get(1).move(0, -shipMaxSpeed);
        sprites.get(0).move(0, 0.5f);
    }

    public void left() {
        sprites.get(1).move(-shipMaxSpeed, 0);
        sprites.get(0).move(0.5f, 0);
    }

    public void right() {
        sprites.get(1).move(shipMaxSpeed, 0);
        sprites.get(0).move(-0.5f, 0);
    }

    public void render() {
        for (Spritable s : sprites) {
            s.render();
        }
    }

    public Ship getShip() {
        return (Ship) sprites.get(1);
    }

    public Spritable get(int i) {
        return sprites.get(i);
    }

    public Fires getWeapon() {
        return (Fires) sprites.get(2);
    }

    public void fireWeapon() throws SlickException {
        if (System.currentTimeMillis() > lastShotFired + 50) {
            getWeapon().fire(new Fire(getShip().getxPos(), getShip().getyPos()));
            lastShotFired = System.currentTimeMillis();
        }
    }

    private void updateMovement() throws SlickException {
        if (in.isKeyDown(Input.KEY_S)) { down(); }
        if (in.isKeyDown(Input.KEY_W)) { up(); }
        if (in.isKeyDown(Input.KEY_A)) { left(); }
        if (in.isKeyDown(Input.KEY_D)) { right(); }
        if (in.isKeyDown(Input.KEY_SPACE)) { fireWeapon(); }
    }
}
