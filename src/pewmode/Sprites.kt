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
    }

    fun update() {
        updateInputMovement()
        sprites.forEach { it.update() }
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

    operator fun get(i: Int): Spritable {
        return sprites[i]
    }

    val weapon: Fires
        get() = sprites[2] as Fires

    @Throws(SlickException::class)
    fun fireWeapon() {
        if (System.currentTimeMillis() > lastShotFired + 50) {
            weapon.fire(Fire(ship.getxPos(), ship.getyPos()))
            lastShotFired = System.currentTimeMillis()
        }
    }

    @Throws(SlickException::class)
    private fun updateInputMovement() {
        if (`in`.isKeyDown(Input.KEY_S)) down()
        if (`in`.isKeyDown(Input.KEY_W)) up()
        if (`in`.isKeyDown(Input.KEY_A)) left()
        if (`in`.isKeyDown(Input.KEY_D)) right()
        if (`in`.isKeyDown(Input.KEY_SPACE)) fireWeapon()
    }
}