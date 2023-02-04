package loggerclibTesting

import loggerclib.channels.ChannelStorage
import loggerclib.channels.tags.ChannelTagStorage
import loggerclib.color.ANSIColor
import loggerclib.color.Color
import loggerclib.color.ColorProperty
import loggerclib.logging.logger
import kotlin.test.Test

class LoggerTest {
    val logger = logger {
        properties {
            displaySuperTag = true
            timeAttributes {
                timeFormat = "HH:mm:ss:SS"
            }
        }
        colorAttributes {
            colorEnabled = true
        }
    }
    private infix fun String.apply(color: ANSIColor): String {
        return "$color$this${color.reset}"
    }
    @Test
    fun testStorage() {
        val redColor = ANSIColor(
            foregroundColor = Color(184u, 256u, 176u),
            backgroundColor = Color(165u),
            colorProperties = arrayOf(
                ColorProperty.Bold,
                ColorProperty.Strikethrough
            )
        )
        println(ChannelStorage.getStorage())
        println(ChannelTagStorage.getStorage())
        println("something" apply redColor)
        assert(true)
        println("stuff")
        println("\u001B[38;2;40;177;249mTRUECOLOR\\x1b[0m\\n")
        println("stuff again")
    }
}