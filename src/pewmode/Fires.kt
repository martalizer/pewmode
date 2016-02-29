package pewmode

import java.util.ArrayList

class Fires : Sprite() {
    internal var fires: MutableList<Spritable> = ArrayList()

    override fun render() {
        fires.forEach { it.render() }
    }

    override fun update() {
        fires.forEach { it.update() }

        if (fires.size > 100) {
            var temp : MutableList<Spritable> = ArrayList()
            fires.forEach { if(it.getxPos() < 3000) temp.add(it) }
            fires = temp
        }
    }

    internal fun fire(s: Spritable) {
        fires.add(s)
    }
}
