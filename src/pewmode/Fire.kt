package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.SlickException

class Fire @Throws(SlickException::class)
constructor(shipXpos: Float, shipYpos: Float) : Sprite() {
    internal var targetspeed: Float = 0.toFloat()
    internal var scaling_done: Boolean = false

    init {
        image = Image("fireball.png")
        setxPos(shipXpos + 220)
        setyPos(shipYpos - 240)
        scale = 0.3f / 10
        targetspeed = (32 * 2).toFloat()
        setxSpeed(targetspeed / 4)
        image.rotate(-45f)
        image.alpha = 0f
        scaling_done = false
    }

    override fun update() {
        if (getxPos() > 3000) return

        if (!scaling_done && scale < TARGET_SCALE) {
            scale += TARGET_SCALE / 10
            if (scale > TARGET_SCALE)
                scaling_done = true
        }
        if (!scaling_done && image.alpha < 1) {
            image.alpha = image.alpha + 0.2f
        }
        if (scaling_done && getxSpeed() < targetspeed) {
            setxSpeed(getxSpeed() + targetspeed / 30)
        }
        setxPos(getxPos() + getxSpeed())
    }

    override fun render() {
        if (getxPos() < 3000) image.draw(getxPos(), getyPos(), scale)
    }

    companion object {
        private val TARGET_SCALE = 0.6f
    }
}

