package pewmode;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Star {
    private Image image;
    float xPos, yPos, speed, scale;
    static List<Star> stars = new ArrayList<>();

    public Star(Image image) {
        initStar();
        this.image = image;
    }

    private void initStar() {
        this.yPos = (float) (Math.random() * 1080);
        this.xPos = -10 + (float) (Math.random() * 2010);
        this.speed = 2 + (float) (Math.random()) * 2;
        this.scale = this.speed / 4;
    }

    private void reset() {
        this.yPos = (float) (Math.random() * 1080);
        xPos = 2000;
    }

    public void draw() {
        image.draw(xPos, yPos, scale);
    }

    public void update() {
        this.xPos -= this.speed;
        if (this.xPos < -10)
            reset();
        if (this.yPos < -10)
            this.yPos = 1080;
        if (yPos > 1090)
            this.yPos = 0;
    }

    public static void createStar(int nbrOfStars) throws SlickException {
        for (int n = 0; n < nbrOfStars; n++) {
            Image star;

            if (n % 2 > 0) star = new Image("star1.png");
            else star = new Image("star2.png");

            stars.add(new Star(star));
        }
    }

    public static void renderStars() {
        for (Star s : stars) {
            s.draw();
        }
    }

    public static void updateStars() {
        for (Star s : stars) {
            s.update();
        }
    }

    public static void move(double x, double y) {
        for (Star s : stars) {
            s.xPos += s.speed * x;
            s.yPos += s.speed * y;
        }
    }
}
