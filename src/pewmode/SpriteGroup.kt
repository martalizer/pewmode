package pewmode

import java.util.*

/**
 * Somewhat hacky way to get around the fact that some Sprites have structure.
 */
abstract class SpriteGroup : Sprite() {
    /**
     * Should allow for mutation of the actual members.
     */
    open fun members(): MutableList<Sprite> {
        return ArrayList()
    }
}
