package app.wishlisted.server.data.model.xbox.product

data class ValidationData(
    val PassedValidation: Boolean?,
    val RevisionId: String,
    val ValidationResultUri: String?
)
