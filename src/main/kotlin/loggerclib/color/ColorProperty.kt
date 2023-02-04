package loggerclib.color

enum class ColorProperty(val code: UShort) {
    Reset(0u),
    Bold(1u),
    Italic(3u),
    Underline(4u),
    Blink(5u),
    Reverse(7u),
    Strikethrough(9u);

    override fun toString(): String {
        return this.code.toString()
    }
}