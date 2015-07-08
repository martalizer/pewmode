package pewmode;

import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Fires extends Sprite {
    List<Spritable> fires = new ArrayList<>();

    public void render() {
        for (Spritable f : fires) {
            f.render();
        }
    }

    public void update() {
        for (Spritable f : fires) {
            f.update();
        }
    }

    public void fire(Spritable s) throws SlickException {
        fires.add(s);
    }
}
