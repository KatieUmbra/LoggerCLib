package dev.kaytea.loggerclib.core

class Format(val fmtString: String) {
    fun formatMessage(data: LoggingData, content: Any, overrideFormat: Format? = null): String {
        val format = data
        return "[${data.channel.name}][${data.tag!!.settings.displayName}]: $content"
    }
}