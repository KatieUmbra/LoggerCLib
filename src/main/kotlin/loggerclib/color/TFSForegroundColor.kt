package loggerclib.color

class TFSForegroundColor(id: UShort): Format {
    private val colorId = id.toInt().coerceIn(31..255)
    override val value = "38;5;$colorId"
    override fun toString(): String { return value }
}