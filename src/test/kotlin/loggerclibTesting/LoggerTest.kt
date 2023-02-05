package loggerclibTesting

import loggerclib.channels.ChannelStorage
import loggerclib.channels.tags.ChannelTagStorage
import loggerclib.color.*
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
    @Test
    fun testStorage() {
        val redColor = StringFormat(EscapeCodes.Unicode, format = arrayOf(
            BasicForegroundColor("Red"),
            Effect("Bold"),
            Effect("Framed")
        ))
        val basicRedColor = StringFormat(EscapeCodes.Unicode, format = arrayOf(
            BasicForegroundColor("Red"),
            Effect("Bold"),
        ))
        val cyanColor = StringFormat(EscapeCodes.Unicode, format = arrayOf(
            BasicForegroundColor("Cyan"),
            Effect("Faint"),
            Effect("Encircled")
        ))
        println( (" 12:14:46 " apply cyanColor)
                + " "
                + (" ERROR " apply redColor)
                + (":" apply basicRedColor)
                + " your mom is gay." )
        assert(true)
    }
}