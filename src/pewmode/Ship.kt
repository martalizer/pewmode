package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.SlickException

class Ship
constructor() : Sprite() {
    init {
        image = Image("ship.png")
    }

    override fun render() {
        image.draw(xPos, yPos, scale)
    }

    override fun move(x: Float, y: Float) {
        if (xPos > (70 - x) && xPos < (1740 - x)) {
            xPos += x
        }

        if (yPos > (-20 - y) && yPos < (990 - y)) {
            yPos += y
        }
    }
}
