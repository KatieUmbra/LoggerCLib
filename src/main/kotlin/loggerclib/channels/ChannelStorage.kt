package loggerclib.channels

object ChannelStorage {
    private val storage = mutableMapOf(
        Channel.buildChannelPair("DEBUG", 15u),
        Channel.buildChannelPair("INFO", 10u),
        Channel.buildChannelPair("WARNING", 5u),
        Channel.buildChannelPair("ERROR", 0u)
    )

    private val protectedChannels = arrayOf("DEBUG", "INFO", "WARNING", "ERROR")

    fun getStorage(): Map<String, Channel> { return storage }
}