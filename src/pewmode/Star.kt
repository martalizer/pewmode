package pewmode

import org.newdawn.slick.Image

class Star(image: Image, internal var screenHeight: Int) : Sprite() {
    init {
        init()
        this.image = image
    }

    override fun init() {
        this.yPos = (Math.random() * screenHeight).toFloat()
        this.xPos = -10 + (Math.random() * 2010).toFloat()
        this.speed = 2 + (Math.random()).toFloat() * 2f * speedmodifier
        this.scale = this.speed / (4 * speedmodifier)
    }

    private fun reset() {
        this.yPos = (Math.random() * screenHeight).toFloat()
        xPos = 2000f
    }

    override fun render() {
        image.draw(xPos, yPos, scale)
        if (alpha < 0.8f)
            setAlpha(alpha + 1f/60f)
    }

    override fun update() {
        this.xPos -= this.speed
        if (this.xPos < -10)
            reset()
        if (this.yPos < -10)
            this.yPos = screenHeight.toFloat()
        if (yPos > screenHeight + 10)
            this.yPos = 0f
    }

    companion object {
        internal var speedmodifier = 4f
    }
}
