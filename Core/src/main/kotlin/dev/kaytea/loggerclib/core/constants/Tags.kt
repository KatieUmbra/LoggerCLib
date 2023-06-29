package dev.kaytea.loggerclib.core.constants

import dev.kaytea.loggerclib.core.Tag
import dev.kaytea.loggerclib.core.dsl.createTag

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