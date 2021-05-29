package app.wishlisted.server.domain.model

data class SearchCriteria(
    val key: String,
    val operation: String,
    val value: Any
)
