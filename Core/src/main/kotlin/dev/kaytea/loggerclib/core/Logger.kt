package dev.kaytea.loggerclib.core

import java.io.InvalidClassException

class Logger <T: Channel> @PublishedApi internal constructor(
    val output: (Any) -> Unit,
    val loggingData: LoggingData
) {
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

        loggingData.tag = tag
        callbacks.forEach { callback ->
            callbackResults[callback.name] = callback.action(this, loggingData)
        }
        output(format.formatMessage(loggingData, content, tag.settings.format))
        action(callbackResults)
    }

    inline fun <reified S: Tag<T>> findObject(): S = S::class.objectInstance ?:
        throw InvalidClassException("The given channel must be a kotlin Object.\n")

    companion object {
        @JvmStatic
        inline fun <reified T : Channel> new(
            noinline output: (Any) -> Unit = ::println
        ): Logger<T> {
            val channel = T::class.objectInstance ?:
                throw InvalidClassException("The given channel must be a kotlin Object.\n")
            return Logger(
                output,
                LoggingData(null, channel)
            )
        }
    }
}