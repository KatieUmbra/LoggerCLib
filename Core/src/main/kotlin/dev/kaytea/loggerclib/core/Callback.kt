package dev.kaytea.loggerclib.core

data class Callback <T> (
    val name: String,
    val action: Logger<*>.(LoggingData) -> T
)

operator fun <T> Collection<Callback<T>>.get(name: String): Callback<T>? {
    return this.find { it.name == name }
}