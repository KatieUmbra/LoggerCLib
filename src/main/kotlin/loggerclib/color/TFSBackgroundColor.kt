package loggerclib.color

class TFSBackgroundColor(id: UShort): Format {
    private val colorId = id.toInt().coerceIn(31..255)
    override val value = "48;5;$colorId"
    override fun toString(): String { return value }
}