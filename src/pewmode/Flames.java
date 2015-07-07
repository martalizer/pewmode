package pewmode;

import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 07/07/15.
 */
public class Flames {

    List<Spritable> flames;

    public Flames() {
        flames = new ArrayList<>();
    }

    public void render() {
        for (Spritable f : flames) {
            f.render();
        }
    }

    public void update() {
        for (Spritable f : flames) {
            f.update();
        }
    }

    public void makeFlame(Spritable s) throws SlickException {
        flames.add(s);
    }

}
