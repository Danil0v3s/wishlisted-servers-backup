package app.wishlisted.server.data.model.xbox.product

data class SkuMarketProperty(
    val FirstAvailableDate: String?,
    val Markets: List<String>,
    val PIFilter: Any?,
    val PackageIds: Any?,
    val SupportedLanguages: List<String>
)
