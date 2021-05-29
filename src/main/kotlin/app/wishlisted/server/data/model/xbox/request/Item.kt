package app.wishlisted.server.data.model.xbox.request

data class Item(
    val Id: String,
    val ItemType: String,
    val PredictedScore: Double,
    val TrackingId: String
)
