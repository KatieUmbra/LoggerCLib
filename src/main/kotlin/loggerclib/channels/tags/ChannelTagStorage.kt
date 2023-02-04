package loggerclib.channels.tags

import loggerclib.exceptions.IllegalTagRequest

object ChannelTagStorage {
    private val storage = mutableMapOf(
        Pair("Super", ChannelTag("Super", UShort.MAX_VALUE))
    )

    operator fun get(name: String): ChannelTag {
        return storage[name] ?: throw IllegalTagRequest("There's no tag with the name $name")
    }

    operator fun plusAssign(element: ChannelTag) {
        if (storage.containsKey(element.name)) throw IllegalTagRequest("The tag named ${element.name} already exists.")
        storage += Pair(element.name, element)
    }

    operator fun minusAssign(element: ChannelTag) {
        with(storage) {
            remove(
                getOrElse(element.name)
                { throw IllegalTagRequest("The requested tag name (${element.name}) does not exist.") }
                    .toString()
            )
        }
    }

    fun getStorage(): Map<String, ChannelTag> { return storage }
}