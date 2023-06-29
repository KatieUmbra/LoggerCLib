package dev.kaytea.loggerclib.core

import dev.kaytea.loggerclib.core.dsl.Callback

class Logger <T: Channel> (builder: () -> String) {
    val loggingData = LoggingData()
    inline fun <reified S: Tag<T>> log(content: Any) {
        @Suppress("UNCHECKED_CAST")
        val callbacks = try {
            S::class
                .members
                .find { it.name == "callbacks" } as List<Callback>
        } catch (_: NullPointerException) {
            null
        }
        val callbackResults = mutableMapOf<String, Any>()
        val tagSettings = S::class.objectInstance!!.settings

        callbacks?.forEach { (n, c, v) ->
            if (v != Unit::class) {
                callbackResults[n] = c(loggingData)
            }
        }

        println("[${tagSettings.displayName}]: $content")
    }
}