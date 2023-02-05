package loggerclib.color

class RGBForegroundColor(r: UShort, g: UShort, b: UShort): Format {
    private val red = r.toInt().coerceAtMost(255)
    private val green = g.toInt().coerceAtMost(255)
    private val blue = b.toInt().coerceAtMost(255)

    override val value = "38;2;$red;$green;$blue"

    override fun toString(): String { return value }
}