package app.wishlisted.server.data.model.xbox.request

data class XboxListResponse(
    val ContinuationToken: String,
    val Id: String,
    val Items: List<Item>,
    val LongTitle: String,
    val Name: String,
    val PagingInfo: PagingInfo,
    val Status: String,
    val Title: String,
    val Version: String
)
