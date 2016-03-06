package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.Sound

class Enemy(xPos: Float) : Sprite() {
    var health = 100
    var lastHit = System.currentTimeMillis()
    val snd = Sound("sounds/explosions/3.wav")

    init {
        this.xPos = xPos
        image = Image("enemy.png")
    }

    override fun render() {
        if (recentlyHit()) {
            image.drawFlash(xPos, yPos)
        } else {
            image.draw(xPos, yPos)
        }
    }

    override fun update() {
        image.rotate(5f)
        xPos -= 2;
        yPos = 500 + 500 * Math.sin(0.01f * xPos.toDouble()).toFloat()
        if (health <= 0) {
            dead = true;
        }
    }

    override fun collision(other: Sprite) {
        if (!recentlyHit()) {
            snd.play()
            health -= 5
            lastHit = System.currentTimeMillis()
//            other.dead = true
        }
    }

    private fun recentlyHit() = System.currentTimeMillis() < lastHit + 100
}
