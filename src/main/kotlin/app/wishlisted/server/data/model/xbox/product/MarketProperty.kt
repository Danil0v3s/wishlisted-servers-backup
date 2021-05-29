package app.wishlisted.server.data.model.xbox.product

data class MarketProperty(
    val BundleConfig: Any?,
    val ContentRatings: List<ContentRating>,
    val Markets: List<String>,
    val MinimumUserAge: Int,
    val OriginalReleaseDate: String?,
    val RelatedProducts: List<RelatedProduct>,
    val UsageData: List<UsageData>
)
