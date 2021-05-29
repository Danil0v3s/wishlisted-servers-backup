package app.wishlisted.server.data.model.xbox.product

data class ContentRating(
    val InteractiveElements: List<String>,
    val RatingDescriptors: List<String>,
    val RatingDisclaimers: List<String>,
    val RatingId: String,
    val RatingSystem: String
)
