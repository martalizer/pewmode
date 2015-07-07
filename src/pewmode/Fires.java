package pewmode;

import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Fires {
    List<Fire> fires = new ArrayList<>();

    public void render() {
        for (Fire f : fires) {
            f.render();
        }
    }

    public void update() {
        for (Fire f : fires) {
            f.update();
        }
    }

    public void fire(float x, float y) throws SlickException {
        fires.add(new Fire(x, y));
    }
}
