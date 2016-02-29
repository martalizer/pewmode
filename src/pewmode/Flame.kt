package pewmode

import org.newdawn.slick.Image

class Flame(image: Image, internal var ship: Ship, offset: Int) : Sprite() {
    internal var flameScale = 1f
    internal var offset = 0

    init {
        this.image = image
        this.offset = offset
    }

    override fun render() {
        val scale = ship.getScale()
        val xPos = ship.getxPos()
        val yPos = ship.getyPos()

        if (image.rotation == 90f) {
            image.draw(xPos - 150 * scale - ((scale * flameScale - 2) * 10),
                    yPos - 70f - ((scale * flameScale - 2) * 60), scale * 5f * flameScale)
        } else {
            image.draw(xPos + offset * scale - ((scale * flameScale - 2) * 10),
                    yPos + 250 * scale, scale * flameScale)
        }
    }

    override fun update() {
        flameScale = (1 + Math.sin(System.nanoTime().toDouble()) / 10).toFloat()

        if (alpha > 0f)
            setAlpha(0f)
        else
            setAlpha(0.8f)

        if (ship.alpha < 1f)    //if ship alpha is below 1 assume no flames since this is only true in menumode
            setAlpha(0f)
    }
}
