package pewmode;

import org.newdawn.slick.Image;

public class Star extends Sprite {
    int screenHeight;
    static int speedmodifier = 4;

    public Star(Image image, int screenHeight) {
        this.screenHeight = screenHeight;
        init();
        this.image = image;
    }

    @Override
    public void init() {
        this.yPos = (float) (Math.random() * screenHeight);
        this.xPos = -10 + (float) (Math.random() * 2010);
        this.speed = 2 + (float) (Math.random()) * 2 * speedmodifier;
        this.scale = this.speed / (4 * speedmodifier);
    }

    private void reset() {
        this.yPos = (float) (Math.random() * screenHeight);
        xPos = 2000;
    }

    @Override
    public void render() {
        image.draw(xPos, yPos, scale);
    }

    @Override
    public void update() {
        this.xPos -= this.speed;
        if (this.xPos < -10)
            reset();
        if (this.yPos < -10)
            this.yPos = screenHeight;
        if (yPos > screenHeight + 10)
            this.yPos = 0;
    }
}
