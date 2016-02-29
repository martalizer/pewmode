package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import java.util.ArrayList

class Stars2
constructor(nbrOfStars: Int, height: Int) : Sprite() {
    internal var stars: MutableList<Spritable> = ArrayList()

    init {
        addStars(nbrOfStars, height)
    }

    @Throws(SlickException::class)
    fun addStars(nbrOfStars: Int, screenHeight: Int) {
        for (n in 1..nbrOfStars) {
            var star: Image

            star = Image("star2.png")

            star.alpha = 0f
            stars.add(Star2(star, screenHeight))
        }
    }

    override fun render() {
        stars.forEach { it.render() }
    }

    override fun update() {
        stars.forEach { it.update() }
    }
}