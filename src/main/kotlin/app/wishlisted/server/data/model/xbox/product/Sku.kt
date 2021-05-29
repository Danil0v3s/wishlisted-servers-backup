package app.wishlisted.server.data.model.xbox.product

data class Sku(
    val LastModifiedDate: String,
    val LocalizedProperties: List<SkuLocalizedProperty>,
    val MarketProperties: List<SkuMarketProperty>,
    val ProductId: String,
    val Properties: SkuProperties,
    val RecurrencePolicy: Any?,
    val SkuASchema: String,
    val SkuBSchema: String,
    val SkuId: String,
    val SkuType: String,
    val SubscriptionPolicyId: Any?
)
