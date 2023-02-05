package loggerclib.color

import loggerclib.exceptions.IllegalANSIProperty

class Effect(name: String): Format {
    override val value = effects.getOrElse(name.uppercase())
    { throw IllegalANSIProperty("There's no effect named $name") }.toString()
    override fun toString(): String { return value }

    companion object {
        private val effects = mapOf<String, UShort>(
            "BOLD" to 1u,
            "FAINT" to 2u,
            "ITALIC" to 3u,
            "UNDERLINE" to 4u,
            "SLOW BLINK" to 5u,
            "RAPID BLINK" to 6u,
            "REVERSE" to 7u,
            "CONCEAL" to 8u,
            "CROSSED OUT" to 9u,
            "DEFAULT FONT" to 10u,
            "ALTERNATE FONT" to 11u,
            "FRAKTUR" to 20u,
            "BOLD OFF" to 21u,
            "DOUBLE UNDERLINE" to 21u,
            "NORMAL" to 22u,
            "UNDERLINE OFF" to 24u,
            "BLINK OFF" to 25u,
            "INVERSE OFF" to 27u,
            "REVEAL" to 28u,
            "NOT CROSSED OUT" to 29u,
            "FRAMED" to 51u,
            "ENCIRCLED" to 52u,
            "OVERLINED" to 53u
        )
    }
}