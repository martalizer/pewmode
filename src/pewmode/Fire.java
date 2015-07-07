package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class Fire extends Sprite {
    static Random rng = new Random();
    float targetspeed, scale;
    boolean scaling_done;
    private static final float TARGET_SCALE = 0.6f;
    float rotationFactor;

    public Fire(float shipXpos, float shipYpos) throws SlickException {
        this.image = new Image("fireball.png");
        this.xPos = shipXpos + 220;
        this.yPos = shipYpos - 240;
        this.scale = 0.3f / 10;
        this.targetspeed = 32;
        this.xSpeed = this.targetspeed / 4;
        this.image.rotate(-45);
        image.setAlpha(0);
        this.scaling_done = false;
        this.rotationFactor = 0;
        //this.rotationFactor = rng.nextInt(4)-2;
        //this.yspeed = (rng.nextFloat()*10f) - 5f;
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

        if (scaling_done) {
            image.rotate(rotationFactor);
        }

        if (scaling_done && xSpeed < targetspeed) {
            xSpeed += targetspeed / 30;
        }

        xPos += xSpeed;
        yPos += ySpeed * xSpeed / 10;
        //rotationFactor = ySpeed / 6f;
    }

    public void render() {
        image.draw(xPos, yPos, scale);
    }
}

