package app.wishlisted.server.data.model.xbox.product

data class SkuProperties(
    val AdditionalIdentifiers: List<Any>,
    val BundledSkus: List<BundledSku>?,
    val DisplayPhysicalStoreInventory: Any?,
    val EarlyAdopterEnrollmentUrl: Any?,
    val FulfillmentPluginId: Any?,
    val FulfillmentType: Any?,
    val HasThirdPartyIAPs: Boolean?,
    val InstallationTerms: String,
    val IsBundle: Boolean?,
    val IsPreOrder: Boolean?,
    val IsRepurchasable: Boolean?,
    val IsTrial: Boolean?,
    val LastUpdateDate: String,
    val SkuDisplayGroupIds: List<String>?,
    val SkuDisplayRank: Int,
    val VersionString: String?,
    val VisibleToB2BServiceIds: List<String>,
    val XboxXPA: Boolean
)
