package app.wishlisted.server.data.model.xbox.product

data class PreviewImage(
    val Caption: String?,
    val FileId: String,
    val FileSizeInBytes: Int,
    val BackgroundColor: Any?,
    val EISListingIdentifier: Any?,
    val ForegroundColor: Any?,
    val ImagePositionInfo: Any?,
    val Height: Int,
    val ImagePurpose: String,
    val UnscaledImageSHA256Hash: String,
    val Uri: String,
    val Width: Int
)
