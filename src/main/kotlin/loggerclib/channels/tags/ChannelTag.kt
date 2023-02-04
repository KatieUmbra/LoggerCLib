package loggerclib.channels.tags

data class ChannelTag(
    val name: String,
    val level: UShort
){
    companion object {
        operator fun get(name: String): ChannelTag {
            return ChannelTagStorage[name]
        }
    }
}