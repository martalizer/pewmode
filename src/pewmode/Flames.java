package pewmode;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Flames extends Sprite {
    List<Spritable> flames = new ArrayList<>();

    Flames(Ship ship) throws SlickException {
        Image rotated = new Image("flame.png");
        rotated.setRotation(90);
        flames.add(new Flame(rotated, ship, 0));

        flames.add(new Flame(new Image("flame.png"), ship, 190));
        flames.add(new Flame(new Image("flame.png"), ship, 210));
        flames.add(new Flame(new Image("flame.png"), ship, 230));
        flames.add(new Flame(new Image("flame.png"), ship, 300));
        flames.add(new Flame(new Image("flame.png"), ship, 320));
        flames.add(new Flame(new Image("flame.png"), ship, 340));

        flames.add(new Flame(new Image("flame.png"), ship, 247));
        flames.add(new Flame(new Image("flame.png"), ship, 267));
        flames.add(new Flame(new Image("flame.png"), ship, 287));
        flames.add(new Flame(new Image("flame.png"), ship, 357));
        flames.add(new Flame(new Image("flame.png"), ship, 377));
        flames.add(new Flame(new Image("flame.png"), ship, 397));
    }

    public void render() {
        if (!menu) {
            for (int i = 0; i < 7; i++) {
                flames.get(i).render();
            }
        } else {
            for (int i = 7; i < 13; i++) {
                flames.get(i).render();
            }
        }
    }

    public void update() {
        for (Spritable s : flames) {
            s.update();
        }
    }
}
