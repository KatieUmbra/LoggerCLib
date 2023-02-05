package loggerclib.color

interface Format {
    val value: String
    override fun toString(): String
}
