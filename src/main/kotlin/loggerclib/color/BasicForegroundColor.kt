package loggerclib.color

import loggerclib.exceptions.IllegalANSIProperty

class BasicForegroundColor(name: String): Format {
    override val value = colors.getOrElse(name.uppercase())
    { throw IllegalANSIProperty("There's no color named $name.") }.toString()

    override fun toString(): String { return value }

    companion object {
        private val colors = mapOf<String, UShort>(
            "BLACK" to 30u,
            "RED" to 31u,
            "GREEN" to 32u,
            "YELLOW" to 33u,
            "BLUE" to 34u,
            "MAGENTA" to 35u,
            "CYAN" to 36u,
            "WHITE" to 37u
        )
    }
}