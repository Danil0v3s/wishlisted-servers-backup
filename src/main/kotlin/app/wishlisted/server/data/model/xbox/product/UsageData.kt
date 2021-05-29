package app.wishlisted.server.data.model.xbox.product

data class UsageData(
    val AggregateTimeSpan: String,
    val AverageRating: Double,
    val PlayCount: Int,
    val PurchaseCount: String,
    val RatingCount: Int,
    val RentalCount: String,
    val TrialCount: String
)
