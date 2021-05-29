package app.wishlisted.server.data.model.xbox.product

data class PIFilter(
    val ExclusionProperties: List<String>,
    val InclusionProperties: List<String>
)
