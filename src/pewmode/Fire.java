package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fire extends Sprite {
    float targetspeed, scale;
    boolean scaling_done;
    private static final float TARGET_SCALE = 0.6f;

    public Fire(float shipXpos, float shipYpos) throws SlickException {
        image = new Image("fireball.png");
        xPos = shipXpos + 220;
        yPos = shipYpos - 240;
        scale = 0.3f / 10;
        targetspeed = 32;
        xSpeed = targetspeed / 4;
        image.rotate(-45);
        image.setAlpha(0);
        scaling_done = false;
    }

    @Override
    public void update() {
        if (!scaling_done && scale < TARGET_SCALE) {
            scale += TARGET_SCALE / 10;
            if (scale > TARGET_SCALE)
                scaling_done = true;
        }
        if (!scaling_done && image.getAlpha() < 1) {
            image.setAlpha(image.getAlpha() + 0.2f);
        }
        if (scaling_done && xSpeed < targetspeed) {
            xSpeed += targetspeed / 30;
        }
        xPos += xSpeed;
    }

    public void render() {
        if (xPos < 3000) image.draw(xPos, yPos, scale);
    }
}

