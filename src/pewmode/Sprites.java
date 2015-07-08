package pewmode;

import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Sprites {
    public ArrayList<Spritable> sprites = new ArrayList<>();;
    public int shipMaxSpeed;
    private long lastShotFired = 0;

    public Sprites(int maxspeed) throws SlickException {
        shipMaxSpeed = maxspeed;
        sprites.add(new Stars(800, 1200));
        sprites.add(new Ship());
        sprites.add(new Fires());
    }

    public void update () {
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
}
