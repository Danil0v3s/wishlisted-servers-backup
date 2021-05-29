package app.wishlisted.server.data.model.xbox.product

data class Conditions(
    val ClientConditions: ClientConditions,
    val EndDate: String,
    val ResourceSetIds: List<String>,
    val StartDate: String
)
