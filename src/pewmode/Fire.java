package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mart on 13/03/15.
 */
public class Fire {
    static Random rng = new Random();
    private Image image;
    float xPos;
    float yPos;
    float speed;
    float yspeed;
    float targetspeed;
    float scale;
    float alpha;
    boolean creationDone;
    static List<Fire> fires = new ArrayList<>();
    private static final float TARGET_SCALE = 0.6f;
    int rotationFactor;

    public Fire(float blahx, float blahy) throws SlickException {
        image = new Image("fireball.png");
        xPos = blahx+220;
        yPos = blahy-240;
        scale = 0.3f/10;
        targetspeed = 32;
        speed = targetspeed/4;
        image.rotate(-45);
        alpha = 0;
        creationDone = false;
        rotationFactor = 0;
        //rotationFactor = rng.nextInt(4)-2;
        yspeed = rng.nextInt(10)-5;
    }

    public static void renderFire() {
        for(Fire f : fires) {
            f.draw();
        }
    }

    public static void updateFires() {
        for(Fire f : fires) {
            f.update();
        }
    }

    public void update() {
        if (!creationDone && scale < TARGET_SCALE) {
            scale += TARGET_SCALE / 10;
            if (scale > TARGET_SCALE)
                creationDone = true;
        }
        if (!creationDone && alpha < 1) {
            alpha += 0.2f;
        }

        if (creationDone) {
            image.rotate(rotationFactor);
        }

        if (creationDone && speed < targetspeed) {
            speed += targetspeed / 30;
        }

        xPos += speed;
        yPos += yspeed*speed/10;
        rotationFactor = (int)yspeed/3;
    }

    public void draw() {
        image.setAlpha(alpha);
        image.draw(xPos, yPos, scale);
    }

    public static void initFire(float blahx, float blahy) throws SlickException {
        fires.add(new Fire(blahx, blahy));
    }
}

