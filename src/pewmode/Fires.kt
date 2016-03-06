package pewmode

import org.newdawn.slick.Sound
import java.util.ArrayList

class Fires : SpriteGroup() {
    internal var fires: MutableList<Sprite> = ArrayList()
    val snd = Sound("sounds/lasers/1.wav")

    override fun render() {
        fires.forEach { it.render() }
    }

    override fun update() {
        fires.forEach { it.update() }

        if (fires.size > 100) {
            var temp : MutableList<Sprite> = ArrayList()
            fires.forEach { if(it.getxPos() < 3000) temp.add(it) }
            fires = temp
        }
    }

    internal fun fire(s: Sprite) {
        snd.play()
        fires.add(s)
    }

    override fun members(): MutableList<Sprite> {
        return fires
    }
}
