package dev.kaytea.loggerclib.core

import dev.kaytea.loggerclib.core.dsl.TagSettings

abstract class Tag <T: Channel> (val settings: TagSettings): ChildOf<T> ()