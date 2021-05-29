package app.wishlisted.server.data.model.xbox.product

data class SatisfyingEntitlementKey(
    val EntitlementKeys: List<String>,
    val LicensingKeyIds: List<String>,
    val PreOrderReleaseDate: String?
)
