package dev.kaytea.loggerclib.core.constants

import dev.kaytea.loggerclib.core.Tag
import dev.kaytea.loggerclib.core.dsl.createTag

object Test: Tag<Super>(createTag {
    displayName = "Test"
    loggingLevel = 124u
})

object Info: Tag<Super>(createTag {
    displayName = "INFO"
    loggingLevel = 3u
})

object Debug: Tag<Super>(createTag {
    displayName = "DEBUG"
    loggingLevel = 2u
})

object Error: Tag<Super>(createTag {
    displayName = "ERROR"
    loggingLevel = 1u
})