package app.wishlisted.server.data.model.xbox.product

data class Video(
    val AudioEncoding: String,
    val Caption: String?,
    val FileSizeInBytes: Int,
    val Height: Int,
    val PreviewImage: PreviewImage,
    val SortOrder: Int,
    val Uri: String,
    val VideoEncoding: String,
    val VideoPositionInfo: String,
    val VideoPurpose: String,
    val Width: Int
)
