package loggerclib.color

class StringFormat(val escapeCode: EscapeCodes, vararg val format: Format) {
    private val valueBuilder = StringBuilder(1000)
    val value: String

    init {
        for (i in format) {
            valueBuilder.append(escapeCode.code, "[", i, "m")
        }
        value = valueBuilder.toString()
    }

    override fun toString(): String { return value }
}

infix fun String.apply(format: StringFormat): String {
    val resetValue = "${format.escapeCode.code}[${Reset}m"
    return "$format$this$resetValue"
}