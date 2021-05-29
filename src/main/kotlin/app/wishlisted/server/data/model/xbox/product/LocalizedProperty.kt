package app.wishlisted.server.data.model.xbox.product

data class LocalizedProperty(
    val DeveloperName: String,
    val EligibilityProperties: EligibilityProperties,
    val Franchises: List<Any>,
    val FriendlyTitle: Any?,
    val Images: List<Image>,
    val Interactive3DEnabled: Boolean?,
    val InteractiveModelConfig: Any?,
    val Language: String,
    val Markets: List<String>,
    val ProductDescription: String,
    val ProductDisplayRanks: List<Any>,
    val ProductTitle: String,
    val PublisherName: String,
    val PublisherWebsiteUri: String?,
    val RenderGroupDetails: Any?,
    val SearchTitles: List<SearchTitle>,
    val ShortDescription: String,
    val ShortTitle: String,
    val SortTitle: String,
    val SupportUri: String?,
    val Videos: List<Video>,
    val VoiceTitle: String
)
