package pewmode

import org.lwjgl.util.Rectangle
import org.newdawn.slick.Image

open class Sprite : Spritable {
    override var dead: Boolean = false
    protected lateinit var image: Image

    internal var scale: Float = 0.toFloat()
    internal var xPos: Float = 0.toFloat()
    internal var yPos: Float = 0.toFloat()
    internal var xSpeed: Float = 0.toFloat()
    internal var ySpeed: Float = 0.toFloat()
    override var speed: Float = 0.toFloat()
    internal var menu: Boolean = false

    override fun getxPos(): Float {
        return xPos
    }

    override fun setMenuMode(menu: Boolean) {
        this.menu = menu
    }

    override fun setxPos(xPos: Float) {
        this.xPos = xPos
    }

    override fun getyPos(): Float {
        return yPos
    }

    override fun setyPos(yPos: Float) {
        this.yPos = yPos
    }

    override fun getxSpeed(): Float {
        return xSpeed
    }

    override fun setxSpeed(xSpeed: Float) {
        this.xSpeed = xSpeed
    }

    override fun getySpeed(): Float {
        return ySpeed
    }

    override fun setySpeed(ySpeed: Float) {
        this.ySpeed = ySpeed
    }

    val alpha: Float
        get() = image.alpha

    override fun setAlpha(alpha: Float) {
        image.alpha = alpha
    }

    fun getScale(): Float {
        return scale
    }

    fun isMenuMode(): Boolean {
        return menu
    }

    override fun setScale(scale: Float) {
        this.scale = scale
    }

    override fun update() {
    }

    override fun render() {
    }

    override fun init() {
    }

    override fun move(x: Float, y: Float) {
        xPos += x
        yPos += y
    }

    override fun blindMove(x: Float, y: Float) {
        xPos += x
        yPos += y
    }

    open fun collision(other: Sprite) {
    }

    fun intersects(b: Sprite): Boolean {
        val ra = Rectangle(
                xPos.toInt(),
                yPos.toInt(),
                image.width,
                image.height
        )
        val rb = Rectangle(
                b.xPos.toInt(),
                b.yPos.toInt(),
                b.image.width,
                b.image.height
        )
        return ra.intersects(rb)
    }
}
