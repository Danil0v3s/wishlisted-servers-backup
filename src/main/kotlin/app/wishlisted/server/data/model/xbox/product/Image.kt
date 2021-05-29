package app.wishlisted.server.data.model.xbox.product

data class Image(
    val BackgroundColor: String?,
    val Caption: String?,
    val EISListingIdentifier: Any?,
    val FileId: String,
    val FileSizeInBytes: Int,
    val ForegroundColor: String?,
    val Height: Int,
    val ImagePositionInfo: String?,
    val ImagePurpose: String,
    val UnscaledImageSHA256Hash: String,
    val Uri: String,
    val Width: Int
)
