package dev.kaytea.loggerclib.core.dsl

import dev.kaytea.loggerclib.core.Callback
import dev.kaytea.loggerclib.core.Format
import dev.kaytea.loggerclib.core.Logger
import dev.kaytea.loggerclib.core.LoggingData

class TagSettings {
    val callbacks = mutableListOf<Callback<Any>>()
    var displayName: String = ""
    var enabled: Boolean = true
    var loggingLevel: UShort = 0u
    var format: Format? = null
    var filter: ((LoggingData) -> Boolean)? = null

    inline fun <reified R : Any> addCallback(
        name: String,
        noinline callback: Logger<*>.(LoggingData) -> R
    ) {
        callbacks.add(Callback(name, callback))
    }

    fun overrideFormat(newFormat: String) {
        this.format = Format(newFormat)
    }

    fun overrideFormat(newFormat: Format) {
        this.format = newFormat
    }
}

fun createTag(tagBuilderScope: TagSettings.() -> Unit): TagSettings {
    val mySettings = TagSettings()
    mySettings.tagBuilderScope()
    return mySettings
}