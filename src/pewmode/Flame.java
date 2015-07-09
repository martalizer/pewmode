package pewmode;

import org.newdawn.slick.Image;

public class Flame extends Sprite {
    float flameScale = 1;
    Ship ship;
    int offset = 0;

    public Flame(Image image, Ship ship, int offset) {
        this.image = image;
        this.offset = offset;
        this.ship = ship;
    }

    @Override
    public void render() {
        float scale = ship.getScale();
        float xPos = ship.getxPos();
        float yPos = ship.getyPos();

        if (image.getRotation() == 90) {
            image.draw(xPos - 150 * scale - ((scale * flameScale - 2) * 10),
                    yPos - 70 - ((scale * flameScale - 2) * 60), scale * 5 * flameScale);
        } else {
            image.draw(xPos + offset * scale - ((scale * flameScale - 2) * 10),
                    yPos + 250 * scale, scale * flameScale);
        }
    }

    @Override
    public void update() {
        flameScale = (float) (1 + Math.sin(System.nanoTime()) / 10);

        if (getAlpha() > 0f)
            setAlpha(0f);
        else
            setAlpha(0.8f);

        if (ship.getAlpha() < 1f)   //if ship alpha is below 1 assume no flames
            setAlpha(0);
    }
}
