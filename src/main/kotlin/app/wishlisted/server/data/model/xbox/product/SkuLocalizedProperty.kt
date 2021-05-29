package app.wishlisted.server.data.model.xbox.product

data class SkuLocalizedProperty(
    val Contributors: List<Any>,
    val DeliveryDateOverlay: Any?,
    val DisplayPlatformProperties: Any?,
    val Features: List<Any>,
    val Images: List<Any>?,
    val Language: String,
    val LegalText: LegalText,
    val Markets: List<String>,
    val MinimumNotes: String,
    val RecommendedNotes: String,
    val ReleaseNotes: String,
    val SkuButtonTitle: String,
    val SkuDescription: String,
    val SkuDisplayRank: List<SkuDisplayRank>,
    val SkuTitle: String,
    val TextResources: Any?
)
