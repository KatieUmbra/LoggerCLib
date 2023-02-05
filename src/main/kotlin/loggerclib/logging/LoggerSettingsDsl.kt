package loggerclib.logging

import loggerclib.Logger
import loggerclib.color.EscapeCodes
import loggerclib.exceptions.IllegalANSIProperty

fun logger(init: LoggerSettings.() -> Unit): Logger {
    val builder = LoggerSettings()
    builder.init()
    val settings = builder.build()
    return Logger(settings)
}

class LoggerSettings() {
    private lateinit var properties: Properties
    private lateinit var colors: Colors

    private constructor(properties: Properties, colors: Colors): this(){
        this.properties = properties
        this.colors = colors
    }

    fun build(): LoggerSettings = LoggerSettings(properties, colors)

    fun properties(init: Properties.() -> Unit) {
        properties = Properties()
        properties.init()
    }

    fun colorAttributes(init: Colors.() -> Unit) {
        colors = Colors()
        colors.init()
    }
}

class Properties {
    var loggingMethod: (Any?) -> Unit = ::println
    var maxVerbosityLevel: UShort = 15u
    var displayTime: Boolean = true
    var displayChannel: Boolean = true
    var displaySuperTag: Boolean = false
    var disableSuperChannels: Boolean = false
    private val enabledTags = mutableListOf<String>()
    private var timeAttributes: TimeAttributes = TimeAttributes()

    fun enableTag(init: () -> String) {
        enabledTags += init()
    }

    fun timeAttributes(init: TimeAttributes.() -> Unit) {
        timeAttributes.init()
    }
}

data class Colors (
    var colorEnabled: Boolean = true,
    var escapeCode: String = "\u001b",
    var timeColor: Triple<UShort, UShort, UShort> = Triple(255u, 255u, 255u),
    var tagColor: Triple<UShort, UShort, UShort> = Triple(255u, 255u, 255u),
    var channelColor: Triple<UShort, UShort, UShort> = Triple(255u, 255u, 255u),
    var messageColor: Triple<UShort, UShort, UShort> = Triple(255u, 255u, 255u),
    var parameterColor: Triple<UShort, UShort, UShort> = Triple(255u, 255u, 255u),
) {
    init {
        var invalidCode = true
        if (colorEnabled) EscapeCodes.values().forEach{ if (it.code == escapeCode) invalidCode = false }
        if (invalidCode) throw IllegalANSIProperty("The code $escapeCode is not a valid escapeCode.")
    }
}

data class TimeAttributes(
    var displayYear: Boolean = false,
    var displayMonth: Boolean = false,
    var displayDay: Boolean = false,
    var displayHours: Boolean = true,
    var displayMinutes: Boolean = true,
    var displaySeconds: Boolean = true,
    var displayMiliSeconds: Boolean = false,
    var timeFormat: String = "HH:mm:ss"
) {
    fun setTimeFormat(init: () -> String) {
        timeFormat = init()
    }
}
