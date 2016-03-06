package pewmode

import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import java.util.*

class Sprites @Throws(SlickException::class)
constructor(var shipMaxSpeed: Int, internal var screenHeight: Int) {
    internal var sprites = ArrayList<Spritable>()
    private var lastShotFired: Long = 0
    internal var `in` = Input(screenHeight)

    init {
        sprites.add(Stars2(3000, screenHeight))
        sprites.add(Ship())
        sprites.add(Fires())
        sprites.add(Flames(ship))
        for(i in 1..10)
            sprites.add(Enemy(2000 + 100 * i.toFloat()))
    }

    fun update() {
        updateInputMovement()
        sprites.forEach { it.update() }

        handleCollisions()
        removeDead()
    }

    private fun handleCollisions() {
        val entities = sprites.filterIsInstance<Sprite>().flatMap {
            if (it is SpriteGroup)
                it.members()
            else
                listOf(it)
        }
        // Naïve O(n^2) collision detection. Use segmentation?
        entities .forEach { that ->
            entities .forEach {
                if(it is Enemy && that is Fire)
                if (it.intersects(that)) {
                    it.collision(that)
                }
            }
        }
    }

    private fun removeDead() {
        sprites.forEach {
            if (it is SpriteGroup) {
                it.members().removeAll { it.dead }
            }
        }
        sprites.removeAll { it.dead }
    }

    fun render() {
        sprites.forEach { it.render() }
    }

    fun init() {
        sprites[1].setxPos(10f)
        sprites[1].setAlpha(0f)
    }

    fun down() {
        sprites[1].move(0f, shipMaxSpeed.toFloat())
        sprites[0].move(0f, -0.5f)
    }

    fun up() {
        sprites[1].move(0f, (-shipMaxSpeed).toFloat())
        sprites[0].move(0f, 0.5f)
    }

    fun left() {
        sprites[1].move((-shipMaxSpeed).toFloat(), 0f)
        sprites[0].move(0.5f, 0f)
    }

    fun right() {
        sprites[1].move(shipMaxSpeed.toFloat(), 0f)
        sprites[0].move(-0.5f, 0f)
    }

    val ship: Ship
        get() = sprites[1] as Ship

    internal operator fun get(i: Int): Spritable {
        return sprites[i]
    }

    val weapon: Fires
        get() = sprites[2] as Fires

    fun fireWeapon() {
        if (System.currentTimeMillis() > lastShotFired + 50) {
            weapon.fire(Fire(ship.getxPos(), ship.getyPos()))
            lastShotFired = System.currentTimeMillis()
        }
    }

    private fun updateInputMovement() {
        if (`in`.isKeyDown(Input.KEY_S)) down()
        if (`in`.isKeyDown(Input.KEY_W)) up()
        if (`in`.isKeyDown(Input.KEY_A)) left()
        if (`in`.isKeyDown(Input.KEY_D)) right()
        if (`in`.isKeyDown(Input.KEY_SPACE)) fireWeapon()
    }
}
