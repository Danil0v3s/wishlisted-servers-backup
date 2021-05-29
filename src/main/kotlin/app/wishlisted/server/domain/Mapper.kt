package app.wishlisted.server.domain

interface Mapper<T, K> {
    fun map(item: T): K
    fun map(items: List<T>): List<K>
}
