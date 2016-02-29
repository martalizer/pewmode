package pewmode

import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import java.util.ArrayList

class Stars
constructor(nbrOfStars: Int, height: Int) : Sprite() {
    internal var stars: MutableList<Spritable> = ArrayList()

    init {
        addStars(nbrOfStars, height)
    }

    fun addStars(nbrOfStars: Int, screenHeight: Int) {
        for (n in 1..nbrOfStars) {
            val star: Image

            if (n % 2 > 0)
               star = Image("star1.png")
            else
                star = Image("star2.png")

            star.alpha = 0f
            stars.add(Star(star, screenHeight))
        }
    }

    override fun render() {
        stars.forEach { it.render() }
    }

    override fun update() {
        stars.forEach { it.update() }
    }

    override fun move(x: Float, y: Float) {
        stars.forEach {
            it.setyPos(it.getyPos() + it.speed * y)
            it.setxPos(it.getxPos() + it.speed * x)
        }
    }
}
