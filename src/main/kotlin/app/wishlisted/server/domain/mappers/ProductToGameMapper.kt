package app.wishlisted.server.domain.mappers

import app.wishlisted.server.data.model.Game
import app.wishlisted.server.data.model.xbox.product.DisplaySkuAvailability
import app.wishlisted.server.data.model.xbox.product.LocalizedProperty
import app.wishlisted.server.data.model.xbox.product.Product
import app.wishlisted.server.domain.Mapper
import org.springframework.beans.factory.annotation.Autowired
import java.util.Locale

class ProductToGameMapper @Autowired constructor() : Mapper<ProductToGameMapper.ProductMarketInput, Game> {

    data class ProductMarketInput(
        val product: Product,
        val market: String,
        val language: String
    )

    private fun mapProduct(product: Product, language: String, market: String): Game {
        val marketProperties = product.MarketProperties.first()
        val localizedProperties = product.LocalizedProperties.first()
        val goldAffirmationIds = mutableListOf<String>()

        val productId = product.ProductId.toUpperCase(Locale.ROOT)
        val releaseDate = marketProperties.OriginalReleaseDate.orEmpty()
        val usageData = marketProperties.UsageData.first()
        val attributes = getAttributes(product)
        val affirmations = getAffirmations(localizedProperties, goldAffirmationIds)
        val eligibility = getEligibility(affirmations)
        val price = getPrice(product.DisplaySkuAvailabilities, eligibility, goldAffirmationIds)
        val images = localizedProperties.Images.map {
            Game.Image(
                purpose = it.ImagePurpose,
                uri = "https:${it.Uri}"
            )
        }
        val videos = localizedProperties.Videos.map {
            Game.Video(
                purpose = it.VideoPurpose,
                uri = it.Uri,
                caption = it.Caption.orEmpty()
            )
        }
        val titleClickName = localizedProperties.ProductTitle.toLowerCase()
            .replace("\\s".toRegex(), "-")
            .replace("[^>a-z0-9-]".toRegex(), "")

        return Game(
            productId = productId,
            productTitle = localizedProperties.ProductTitle,
            releaseDate = releaseDate,
            categories = when {
                !product.Properties.Categories.isNullOrEmpty() -> product.Properties.Categories
                product.Properties.Category != null -> listOf(product.Properties.Category)
                else -> emptyList()
            },
            rating = Game.Rating(
                averageRating = usageData.AverageRating,
                ratingCount = usageData.RatingCount
            ),
            attributes = attributes,
            eligibility = eligibility,
            price = price,
            images = images,
            href = "https://www.microsoft.com/$language-$market/p/$titleClickName/$productId",
            description = localizedProperties.ProductDescription,
            shortDescription = localizedProperties.ShortDescription,
            videos = videos,
            publisher = localizedProperties.PublisherName,
            language = language,
            market = market
        )
    }

    private fun getAttributes(product: Product): List<Game.Attribute> {
        return product.Properties.Attributes
            ?.filter { it.Maximum != null && it.Minimum != null }
            ?.map {
                Game.Attribute(
                    name = it.Name,
                    minimum = it.Minimum ?: 0,
                    maximum = it.Maximum ?: 0
                )
            } ?: emptyList()
    }

    private fun getAffirmations(localizedProperties: LocalizedProperty, goldAffirmationIds: MutableList<String>): List<String> {
        return localizedProperties.EligibilityProperties.Affirmations
            .map {
                if (it.Description.orEmpty().toLowerCase().indexOf("gold") >= 0) {
                    it.AffirmationProductId?.also {
                        goldAffirmationIds.add(it)
                    }
                }
                it.AffirmationId.orEmpty()
            }
            .filter { it.isNotEmpty() }
    }

    private fun getEligibility(affirmations: List<String>): Game.Eligibility {
        return Game.Eligibility(
            gamePass = affirmations.contains(AffirmationIds.GAME_PASS) || affirmations.contains(AffirmationIds.GAME_PASS_2),
            gamePassUltimate = affirmations.contains(AffirmationIds.GAME_PASS_ULTIMATE),
            eaPlay = affirmations.contains(AffirmationIds.EA_PLAY),
            gold = affirmations.contains(AffirmationIds.GAMES_WITH_GOLD)
        )
    }

    private fun getPrice(
        displaySkuAvailabilities: List<DisplaySkuAvailability>,
        eligibility: Game.Eligibility,
        goldAffirmationIds: MutableList<String>
    ): Game.Price {
        var purchnum = 0
        var isPurchasable = false
        var isGoldSale = false
        var hasGoldDiscount = false
        var isOnSale = false
        var specialPrice = 0.0
        var gameListPrice = 0.0
        var gameMsrpPrice = 0.0
        var goldPrice = 0.0
        var currencyCode = ""

        displaySkuAvailabilities.forEach { sku ->
            sku.Availabilities.forEachIndexed { availabilityIndex, availability ->
                val isPurchaseActionAvailable = availability.Actions.indexOf("Purchase") >= 0
                val msrp = availability.OrderManagementData.Price.MSRP
                val listPrice = availability.OrderManagementData.Price.ListPrice
                val isTrial = sku.Sku.Properties.IsTrial ?: false

                if (isPurchaseActionAvailable) {
                    isPurchasable = true
                    purchnum++
                    isGoldSale = purchnum > 0 &&
                        eligibility.gold &&
                        availability.RemediationRequired == true &&
                        goldAffirmationIds.indexOf(availability.Remediations?.firstOrNull()?.BigId) >= 0

                    if ((msrp != 0.0 || (msrp == 0.0 && listPrice == 0.0)) && !isTrial) {

                        if ((listPrice != msrp || (msrp == 0.0 && listPrice == 0.0)) && availabilityIndex != 0) {
                            specialPrice = listPrice
                        } else {
                            gameListPrice = listPrice
                        }

                        if (availabilityIndex == 0) {
                            gameMsrpPrice = msrp
                        }

                        currencyCode = availability.OrderManagementData.Price.CurrencyCode

                        if (availability.Properties.MerchandisingTags != null) {
                            if (availability.Properties.MerchandisingTags.indexOf("LegacyGamesWithGold") >= 0) {
                                // TODO GamesWithGold = true;
                                specialPrice = gameListPrice
                                gameListPrice = gameMsrpPrice
                            }

                            hasGoldDiscount = availability.Properties.MerchandisingTags.indexOf("LegacyDiscountGold") >= 0
                        }

                        if (isGoldSale && availability.DisplayRank == 1) {
                            goldPrice = listPrice
                        }

                        // TODO EaAccessGame = TempEa === true && av.Actions.length === 2;
                        isOnSale = gameListPrice < gameMsrpPrice
                    }
                }
            }
        }

        return Game.Price(
            isPurchasable,
            isGoldSale,
            specialPrice,
            gameListPrice,
            gameMsrpPrice,
            currencyCode,
            isOnSale,
            goldPrice,
            hasGoldDiscount
        )
    }

    override fun map(item: ProductMarketInput): Game =
        mapProduct(item.product, item.language, item.market)

    override fun map(items: List<ProductMarketInput>): List<Game> =
        items.map { mapProduct(it.product, it.language, it.market) }

    private object AffirmationIds {
        const val EA_PLAY = "B0HFJ7PW900M"
        const val GAME_PASS = "9WNZS2ZC9L74"
        const val GAME_PASS_2 = "9VP428G6BQ82"
        const val GAME_PASS_ULTIMATE = "9NC1XH2KD60Z"
        const val GAMES_WITH_GOLD = "<TODO REPLACE WITH GOLD ID>"
    }
}
