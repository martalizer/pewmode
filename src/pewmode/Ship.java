package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Ship extends Sprite {
    public Ship() throws SlickException {
        this.image = new Image("ship.png");
        init();
    }

    public void render() {
        image.draw(xPos, yPos, scale);
    }

    @Override
    public void move(float x, float y) {
        if (xPos > (70 - x) && xPos < (1740 - x)) {
            xPos += x;
        }
        if (yPos > (-20  - y) && yPos < (1110 - y)) {
            yPos += y;
        }
    }
}
