package dev.kaytea.loggerclib.core

import dev.kaytea.loggerclib.core.dsl.TagSettings
import dev.kaytea.loggerclib.core.dsl.createTag

abstract class Tag <T: Channel> (val settings: TagSettings): ChildOf<T>()

class Debug: Tag<Channel>(createTag {
    displayName = "SDJKFASHJKFD"
    loggingLevel = 3u
})