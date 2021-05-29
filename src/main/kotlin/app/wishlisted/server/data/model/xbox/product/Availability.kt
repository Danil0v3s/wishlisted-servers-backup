package app.wishlisted.server.data.model.xbox.product

data class Availability(
    val Actions: List<String>,
    val AffirmationId: String?,
    val AlternateIds: List<AvailabilityAlternateId>?,
    val AvailabilityASchema: String,
    val AvailabilityBSchema: String,
    val AvailabilityId: String,
    val Conditions: Conditions,
    val DisplayRank: Int,
    val LastModifiedDate: String,
    val LicensingData: LicensingData?,
    val Markets: List<String>,
    val OrderManagementData: OrderManagementData,
    val Properties: AvailabilityProperties,
    val RemediationRequired: Boolean?,
    val Remediations: List<AvailabilityRemediation>?,
    val SkuId: String
)
