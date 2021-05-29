package app.wishlisted.server.data.model.xbox.product

data class Product(
    val AlternateIds: List<AlternateId>,
    val DisplaySkuAvailabilities: List<DisplaySkuAvailability>,
    val DomainDataVersion: Any?,
    val IngestionSource: String,
    val IsMicrosoftProduct: Boolean?,
    val IsSandboxedProduct: Boolean?,
    val LastModifiedDate: String,
    val LocalizedProperties: List<LocalizedProperty>,
    val MarketProperties: List<MarketProperty>,
    val MerchandizingTags: List<Any>,
    val PartD: String?,
    val PreferredSkuId: String,
    val ProductASchema: String,
    val ProductBSchema: String,
    val ProductFamily: String,
    val ProductId: String,
    val ProductKind: String,
    val ProductType: String,
    val Properties: Properties,
    val SandboxId: String?,
    val SchemaVersion: String,
    val ValidationData: ValidationData
)
