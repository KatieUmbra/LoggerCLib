package dev.kaytea.loggerclib.coreTest

import dev.kaytea.loggerclib.core.Logger
import dev.kaytea.loggerclib.core.constants.Error
import dev.kaytea.loggerclib.core.constants.Debug
import dev.kaytea.loggerclib.core.constants.Info
import dev.kaytea.loggerclib.core.constants.Super
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

val mainLogger = Logger<Super> { "" }

class LoggerTest {
    @Test
    fun printsTest() {
        assertDoesNotThrow {
            mainLogger.log<Info>("Lol lmaoooo")
            mainLogger.log<Debug>("I'm debugging here")
            mainLogger.log<Error>("what the fuck")
        }
    }
}