package loggerclib.color

import loggerclib.exceptions.IllegalANSIProperty

class BasicBackgroundColor(name: String): Format {
    override val value = colors.getOrElse(name.uppercase())
    { throw IllegalANSIProperty("There's no color named $name.") }.toString()

    override fun toString(): String { return value }

    companion object {
        private val colors = mapOf<String, UShort>(
            "BLACK" to 40u,
            "RED" to 41u,
            "GREEN" to 42u,
            "YELLOW" to 43u,
            "BLUE" to 44u,
            "MAGENTA" to 45u,
            "CYAN" to 46u,
            "WHITE" to 47u
        )
    }
}