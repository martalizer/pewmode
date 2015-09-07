package pewmode;

import org.newdawn.slick.Image;

public class Sprite implements Spritable {
    protected Image image;
    boolean menu;
    float scale , speed,
          xPos  , yPos,
          xSpeed, ySpeed;

    public float getxPos() {
        return xPos;
    }

    @Override
    public void menu(boolean menu) {
        this.menu = menu;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    public float getAlpha() {
        return image.getAlpha();
    }
    public void setAlpha(float alpha) {
         image.setAlpha(alpha);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void update() {}
    public void render() {}
    public void init() {}

    public void move(float x, float y) {
        xPos += x;
        yPos += y;
    }

    public void blindMove(float x, float y) {
        xPos += x;
        yPos += y;
    }
}
