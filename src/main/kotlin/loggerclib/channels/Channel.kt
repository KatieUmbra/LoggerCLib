package loggerclib.channels

import loggerclib.channels.tags.ChannelTag

data class Channel(
    val name: String,
    val level: UShort,
    val parent: ChannelTag = ChannelTag["Super"]
) {
    constructor(
        name: String,
        level: UShort,
        parent: String = "Super"
    ): this(
        name,
        level,
        ChannelTag[parent]
    )
    companion object {
        fun buildChannelPair(name: String, level: UShort, parent: String = "Super"): Pair<String, Channel> {
            return Pair(name, Channel(name, level, parent))
        }
    }
}