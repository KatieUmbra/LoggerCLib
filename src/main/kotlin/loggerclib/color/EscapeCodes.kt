package loggerclib.color

enum class EscapeCodes(val code: String) {
    CtrlKey("^["),
    Unicode("\u001b"),
    Decimal("27");

    override fun toString(): String {
        return this.code
    }
}