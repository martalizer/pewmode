package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fire {
    static Random rng = new Random();
    private Image image;
    float xPos, yPos, speed, yspeed, targetspeed, scale, alpha;
    boolean creationDone;
    static List<Fire> fires = new ArrayList<>();
    private static final float TARGET_SCALE = 0.6f;
    float rotationFactor;

    public Fire(float shipXpos, float shipYpos) throws SlickException {
        this.image = new Image("fireball.png");
        this.xPos = shipXpos + 220;
        this.yPos = shipYpos - 240;
        this.scale = 0.3f / 10;
        this.targetspeed = 32;
        this.speed = this.targetspeed / 4;
        this.image.rotate(-45);
        this.alpha = 0;
        this.creationDone = false;
        this.rotationFactor = 0;
        //this.rotationFactor = rng.nextInt(4)-2;
        this.yspeed = (rng.nextFloat()*10f) - 5f;
    }

    public static void renderFire() {
        for (Fire f : fires) {
            f.draw();
        }
    }

    public static void updateFires() {
        for (Fire f : fires) {
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
        yPos += yspeed * speed / 10;
        rotationFactor = yspeed / 6f;
    }

    public void draw() {
        image.setAlpha(alpha);
        image.draw(xPos, yPos, scale);
    }

    public static void initFire(float blahx, float blahy) throws SlickException {
        fires.add(new Fire(blahx, blahy));
    }
}

