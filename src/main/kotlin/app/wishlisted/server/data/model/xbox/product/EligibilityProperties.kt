package app.wishlisted.server.data.model.xbox.product

data class EligibilityProperties(
    val Affirmations: List<Affirmation>,
    val Remediations: List<EligibilityPropertiesRemediation>
)
