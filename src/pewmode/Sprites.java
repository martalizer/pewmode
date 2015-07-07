package pewmode;

import org.newdawn.slick.SlickException;

public class Sprites {
    public Ship ship;
    public Stars stars;
    public Fires fires;
    public Flames flames_in_menu;
    public Flames flames;
    public int shipMaxSpeed;

    public Sprites(int maxspeed) throws SlickException {
        shipMaxSpeed = maxspeed;
        this.ship = new Ship();
        this.fires = new Fires();
        this.stars = new Stars();
        this.flames = new Flames();
    }

    public void update () {
        stars.update();
        fires.update();
        flames.update();
    }

    public void init() throws SlickException {
        stars.addStar(400, 1200);
        ship.setxPos(10);
        ship.setxPos(10);
        ship.setAlpha(0);
    }

    public void down() {
        ship.move(0, shipMaxSpeed);
        stars.move(0, -0.5f);
    }

    public void up() {
        ship.move(0, -shipMaxSpeed);
        stars.move(0, 0.5f);
    }

    public void left() {
        ship.move(-shipMaxSpeed, 0);
        stars.move(0.5f, 0);
    }

    public void right() {
        ship.move(shipMaxSpeed, 0);
        stars.move(-0.5f, 0);
    }

    public void render() {
        stars.render();
        fires.render();
        ship.render();
        flames.render();
    }
}
