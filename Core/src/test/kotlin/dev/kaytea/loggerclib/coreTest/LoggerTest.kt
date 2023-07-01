package dev.kaytea.loggerclib.coreTest

import dev.kaytea.loggerclib.core.Logger
import dev.kaytea.loggerclib.core.constants.Error
import dev.kaytea.loggerclib.core.constants.Debug
import dev.kaytea.loggerclib.core.constants.Info
import dev.kaytea.loggerclib.core.constants.Super
import dev.kaytea.loggerclib.core.constants.Test as TestExample
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals

class LoggerTest {
    @Test
    fun printsTest() {
        assertDoesNotThrow {
            Info.settings.addCallback("Channel") {
                it.channel.name
            }
            val mainLogger = Logger.new<Super>()
            mainLogger.log<Info>("Lol lmaooo") {
                println(it["Channel"])
            }
            mainLogger.log<Debug>("I'm debugging here")
            mainLogger.log<Error>("what the fuck")
            mainLogger.log<TestExample>("Yooo this shouldn't happen")
        }
    }
    @Test
    fun outputTest() {
        val expected = listOf("[Super][INFO]: Lol lmaooo",
                "[Super][DEBUG]: I'm debugging here",
                "[Super][ERROR]: what the fuck")
        val actual = mutableListOf<String>()
        val mainLogger = Logger.new<Super> {
            actual.add(it.toString())
        }
        mainLogger.log<Info>("Lol lmaooo")
        mainLogger.log<Debug>("I'm debugging here")
        mainLogger.log<Error>("what the fuck")
        assertEquals(expected, actual)
    }
}