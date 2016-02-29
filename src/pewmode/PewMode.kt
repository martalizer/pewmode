package pewmode

import org.lwjgl.input.Mouse
import org.newdawn.slick.*
import org.newdawn.slick.util.Log
import java.util.*

class PewMode(gamename: String) : BasicGame(gamename) {
    var fade = 0f
    var countdown = 30
    internal lateinit  var pewmodetitle: Image
    internal lateinit var pressanykey: Image
    internal lateinit var sprites: Sprites
    internal var menumode = true
    internal var firstime = true
    internal lateinit var timestamp : Date
    //internal var runOnce = true;
    internal val MAX_SHIP_SPEED = 12
    val screenHeight = 1080

    override fun init(gc: GameContainer) {
        Mouse.setGrabbed(true)
        sprites = Sprites(MAX_SHIP_SPEED, screenHeight)
        sprites.init()
        pewmodetitle = Image("title.png")
        pewmodetitle.alpha = 0f
        pressanykey = Image("anykey.png")
        pressanykey.alpha = 0f
    }

    override fun keyPressed(key: Int, c: Char) {
        if (menumode) {
            menumode = false
            sprites.ship.setAlpha(1f)
            sprites.ship.setScale(0.3f)
            sprites.ship.setxPos(200f)
            sprites.ship.setyPos(400f)
            super.keyPressed(key, c)
        }
    }

    override fun update(gc: GameContainer, i: Int) {
        sprites.update()

        if (menumode)
            updateIntroAndMenu()
    }

    override fun render(gc: GameContainer, g: Graphics) {
        if (menumode) renderMenu()
        else renderGame()
    }

    private fun renderGame() {
        if (firstime) {
            timestamp = Date()
            sprites.sprites[0] = Stars(3000, screenHeight)
            firstime = false
        }

//        if (Date().time - timestamp.time > 5000 && runonce) {
//            println(Date().time - timestamp.time)
//            runOnce = false
//        }

        sprites.get(3).setMenuMode(false) //disable rear flame when in menu mode
        sprites.render()
    }

    private fun renderMenu() {
        sprites.get(3).setMenuMode(true)
        sprites.render()

        pressanykey.draw(590f, 500f, 1f)
        pewmodetitle.draw(88f, 100f, 4.3f)
    }

    private fun updateIntroAndMenu() {
        //sprites.ship.setAlpha(0f)
        if (sprites.ship.alpha < 1f) {
            sprites.ship.setAlpha(sprites.ship.alpha + 0.004f)
            sprites.ship.setScale(sprites.ship.getxPos() * sprites.ship.getyPos() / 60000.00f)
            sprites.ship.blindMove(2f, 0f)
        }

        if (570 + sprites.ship.getyPos() < screenHeight) {
            sprites.ship.blindMove(0f, 0.9f)
        } else {
            pewmodetitle.alpha = (pewmodetitle.alpha + 0.05f)
        }

        if (pewmodetitle.alpha > 1) {
            if (countdown > 0)
                countdown--
            else {
                val sinblinkthing = Math.abs(Math.sin(System.nanoTime() / (10e7) * 0.7).toFloat())
                pressanykey.setAlpha(sinblinkthing.toFloat() * fade)
                if (fade < 1)
                    fade += 0.1f
            }
        }
    }

    companion  object {
        @JvmStatic fun main(args: Array<String>) {
            val appgc: AppGameContainer
            val pm = PewMode("PewMode!")
            val sg = ScalableGame(pm, 1920, pm.screenHeight, true)
            appgc = AppGameContainer(sg)
            appgc.setDisplayMode(appgc.screenWidth, appgc.screenHeight, true)
            appgc.setShowFPS(false)
            appgc.setTargetFrameRate(60)
            appgc.setVSync(true)
            appgc.start()
        }
    }
}