package dev.kaytea.loggerclib.core

import java.io.InvalidClassException

class Logger <T: Channel> @PublishedApi internal constructor() {
    @PublishedApi
    internal lateinit var output: (Any) -> Unit
    @PublishedApi
    internal lateinit var channel: T
    @PublishedApi
    internal lateinit var loggingData: LoggingData
    val format = Format("")

    inline fun <reified S: Tag<T>> log(
        content: Any,
        action: (Map<String, Any>) -> Unit = {}
    ) {
        val tag = findObject<S>()

        if (!tag.settings.enabled) return
        if (tag.settings.filter?.let { it(loggingData) } == true) return

        val callbacks = tag.settings.callbacks
        val callbackResults = mutableMapOf<String, Any>()

        loggingData.tag = S::class.objectInstance!!
        callbacks.forEach { callback ->
            callbackResults[callback.name] = callback.action(this, loggingData)
        }
        output(format.formatMessage(loggingData, content, tag.settings.format))
        action(callbackResults)
    }

    inline fun <reified S: Tag<T>> findObject(): S = S::class.objectInstance!!

    companion object {
        @JvmStatic
        inline fun <reified T: Channel> new(
            noinline output: (Any) -> Unit = ::println
        ): Logger<T> {
            val logger = Logger<T>()
            logger.channel = T::class.objectInstance ?:
                throw InvalidClassException("The given channel must be a kotlin Object.")
            logger.output = output
            logger.loggingData = LoggingData(null, logger.channel)
            return logger
        }
    }
}