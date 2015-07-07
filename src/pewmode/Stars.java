package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Stars {
    List<Star> stars = new ArrayList<>();

    public void addStar(int nbrOfStars, int screenHeight) throws SlickException {
        for (int n = 0; n < nbrOfStars; n++) {
            Image star;

            if (n % 2 > 0) star = new Image("star1.png");
            else star = new Image("star2.png");

            stars.add(new Star(star, screenHeight));
        }
    }

    public void render() {
        for (Star s : stars) {
            s.render();
        }
    }

    public void update() {
        for (Star s : stars) {
            s.update();
        }
    }

    public void move(double x, double y) {
        for (Star s : stars) {
            s.xPos += s.speed * x;
            s.yPos += s.speed * y;
        }
    }
}
