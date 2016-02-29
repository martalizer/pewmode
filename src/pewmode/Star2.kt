package pewmode

import org.newdawn.slick.Image

class Star2(image: Image, internal var screenHeight: Int) : Sprite() {
    private var initAlphaModifier = 1f;
    init {
        init()
        this.image = image
    }

    override fun init() {
        this.yPos = (Math.random() * screenHeight).toFloat()
        this.xPos = -10 + (Math.random() * 2010).toFloat()
        this.speed = 7 + (Math.random()).toFloat() * 2f * speedmodifier.toFloat()
        this.scale = 1f

        // warmup to get a nice star spread.
        for(i in 1..1000) {
            updateMovement()
        }
    }

    private fun reset() {
        xPos = (1920/2).toFloat() + (Math.random() * 10).toFloat() - 5
        yPos = (screenHeight/2).toFloat() + (Math.random() * 10).toFloat() - 5
    }

    override fun render() {
        image.draw(xPos, yPos, scale)
    }

    fun updateMovement() {
        // Base movement amount (per frame) on distance from center of screen
        if (xPos < 1920/2) {
            xPos += ((xPos - 1920/2)*speed/500)
        } else {
            xPos -= ((1920/2 - xPos)*speed/500)
        }

        if (yPos > screenHeight/2) {
            yPos += (yPos - screenHeight/2)*speed/500
        } else {
            yPos -= (screenHeight/2 -yPos)*speed/500
        }

        if (this.xPos < -10)
            reset()
        if (this.xPos > 1930)
            reset()
        if (this.yPos < -10)
            reset()
        if (yPos > screenHeight + 10)
            reset()
    }

    override fun update() {
        updateMovement()

        var distanceX = Math.abs(xPos - 1920/2)
        var distanceY = Math.abs(yPos - screenHeight/2)
        setAlpha(distanceX/600 + distanceY/400)

        if (initAlphaModifier > 0f) {
            setAlpha(alpha - initAlphaModifier)
            initAlphaModifier -= 1f/100
        }
    }

    companion object {
        internal var speedmodifier = 4
    }
}
