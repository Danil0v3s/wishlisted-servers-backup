package app.wishlisted.server.data.model

class Game(
    var gameId: Int = 0,
    var productId: String,
    var categories: List<String>,
    var releaseDate: String,
    var rating: Rating,
    var attributes: List<Attribute>,
    var productTitle: String,
    var eligibility: Eligibility,
    var price: Price,
    var images: List<Image>,
    var href: String,
    var description: String,
    var shortDescription: String,
    var videos: List<Video>,
    var publisher: String,
    var language: String,
    var market: String
) {
    class Rating(
        var averageRating: Double,
        var ratingCount: Int
    )

    class Attribute(
        var name: String,
        var minimum: Int,
        var maximum: Int
    )

    class Eligibility(
        var gamePass: Boolean,
        var eaPlay: Boolean,
        var gamePassUltimate: Boolean,
        var gold: Boolean
    )

    class Price(
        var isPurchasable: Boolean,
        var isGoldSale: Boolean,
        var specialPrice: Double,
        var gameListPrice: Double,
        var gameMsrpPrice: Double,
        var currencyCode: String,
        var isOnSale: Boolean,
        var goldPrice: Double,
        var hasGoldDiscount: Boolean
    )

    class Image(
        var purpose: String,
        var uri: String
    )

    class Video(
        var purpose: String,
        var uri: String,
        var caption: String
    )
}
