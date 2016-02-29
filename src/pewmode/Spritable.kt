package pewmode

internal interface Spritable {
    fun getxPos(): Float

    fun setxPos(xPos: Float)

    fun getyPos(): Float

    fun setyPos(yPos: Float)

    fun getxSpeed(): Float

    fun setxSpeed(xSpeed: Float)

    fun getySpeed(): Float

    fun setySpeed(ySpeed: Float)

    var speed: Float

    fun update()

    fun render()

    fun init()

    fun move(x: Float, y: Float)

    fun blindMove(x: Float, y: Float)

    fun setAlpha(i: Float)

    fun setScale(v: Float)

    fun setMenuMode(menu: Boolean)
}
