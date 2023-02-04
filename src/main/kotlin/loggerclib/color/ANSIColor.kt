@file:Suppress("FunctionName")

package loggerclib.color

import loggerclib.logging.EscapeCodes

class ANSIColor(
    escapeCode: EscapeCodes = EscapeCodes.Unicode,
    foregroundColor: Color = Color("White"),
    backgroundColor: Color = Color("Reset"),
    vararg colorProperties: ColorProperty = arrayOf(ColorProperty.Reset)
) {
    private val ansiBuilder = StringBuilder(50)
    private val ansiCode: String
    val reset = "$escapeCode[0m"

    init {
        ansiBuilder.append(escapeCode, "[")
        when (foregroundColor.kind) {
            Color.Companion.CodeKind.AnsiCode -> ansiBuilder.append(foregroundColor)
            Color.Companion.CodeKind.Id -> ansiBuilder.append("38;5;", foregroundColor, ";")
            Color.Companion.CodeKind.Rgb -> ansiBuilder.append("38;2;", foregroundColor)
            Color.Companion.CodeKind.Reset -> Unit
        }
        /*when (backgroundColor.kind) {
            Color.Companion.CodeKind.AnsiCode -> ansiBuilder.append(10+"$backgroundColor".toInt())
            Color.Companion.CodeKind.Id -> ansiBuilder.append("48;5;", backgroundColor, ";")
            Color.Companion.CodeKind.Rgb -> ansiBuilder.append("48;2;", backgroundColor, ";")
            Color.Companion.CodeKind.Reset -> Unit
        }*/
        for (i in colorProperties) {
            ansiBuilder.append(i.code, ";")
        }
        ansiCode = ansiBuilder.removeSuffix(";").toString() + "m"
    }

    override fun toString(): String {
        return ansiCode
    }
}