package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.SlickException

import java.util.ArrayList

class Flames @Throws(SlickException::class)
internal constructor(ship: Ship) : SpriteGroup() {
    internal var flames: MutableList<Sprite> = ArrayList()

    init {
        val rotated = Image("flame.png")
        rotated.rotation = 90f
        flames.add(Flame(rotated, ship, 0))

        flames.add(Flame(Image("flame.png"), ship, 190))
        flames.add(Flame(Image("flame.png"), ship, 210))
        flames.add(Flame(Image("flame.png"), ship, 230))
        flames.add(Flame(Image("flame.png"), ship, 300))
        flames.add(Flame(Image("flame.png"), ship, 320))
        flames.add(Flame(Image("flame.png"), ship, 340))

        flames.add(Flame(Image("flame.png"), ship, 247))
        flames.add(Flame(Image("flame.png"), ship, 267))
        flames.add(Flame(Image("flame.png"), ship, 287))
        flames.add(Flame(Image("flame.png"), ship, 357))
        flames.add(Flame(Image("flame.png"), ship, 377))
        flames.add(Flame(Image("flame.png"), ship, 397))
    }

    override fun render() {
        if (!isMenuMode()) {
            for (i in 0..6) {
                flames[i].render()
            }
        } else {
            for (i in 7..12) {
                flames[i].render()
            }
        }
    }

    override fun update() {
        flames.forEach { it.update() }
    }
}
