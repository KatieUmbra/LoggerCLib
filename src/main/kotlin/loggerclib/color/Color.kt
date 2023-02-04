package loggerclib.color

import loggerclib.exceptions.IllegalANSIProperty
import java.lang.StringBuilder

class Color{
    private val colorCode: String
    val kind: CodeKind

    constructor(name: String) {
        if (name.uppercase() == "RESET") { colorCode = "0"; kind = CodeKind.Reset; return }
        colorCode = nameCode.getOrElse(name.uppercase()) {
            throw IllegalANSIProperty("There's no ANSI color code named $name")
        }.toString()
        kind = CodeKind.AnsiCode
    }
    constructor(r: UShort, g: UShort, b: UShort) {
        val rgb = arrayOf(0, 0, 0)
        rgb[0] = r.toInt().coerceAtMost(256)
        rgb[1] = g.toInt().coerceAtMost(256)
        rgb[2] = b.toInt().coerceAtMost(256)
        val builder = StringBuilder(11)
        for (i in rgb) {
            builder.append(i, ";")
        }
        with(builder) { deleteAt(lastIndex) }
        colorCode = builder.toString()
        kind = CodeKind.Rgb
    }
    constructor(id: UShort) {
        val ide = id.coerceAtMost(256u)
        colorCode = ide.toString()
        kind = CodeKind.Id
    }

    override fun toString(): String {
        return this.colorCode
    }

    companion object {
        enum class CodeKind {
            AnsiCode,
            Id,
            Rgb,
            Reset
        }
        private val nameCode = mapOf<String, UShort>(
            "DEFAULT" to 39u,
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