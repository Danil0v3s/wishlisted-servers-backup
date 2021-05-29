package app.wishlisted.server.data.service

import app.wishlisted.server.data.api.XboxApi
import app.wishlisted.server.data.model.Game
import app.wishlisted.server.data.model.xbox.request.Item
import app.wishlisted.server.data.model.xbox.request.XboxListResponse
import app.wishlisted.server.domain.Mapper
import app.wishlisted.server.domain.mappers.ProductToGameMapper
import com.squareup.moshi.Moshi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import retrofit2.Call

@Service
class GameService @Autowired constructor(
    private val xboxApi: XboxApi,
    private val moshi: Moshi,
    private val gameMapper: Mapper<ProductToGameMapper.ProductMarketInput, Game>
) {

    @Value("\${wishlisted.xbox.endpoints.products}")
    private lateinit var xboxProductDetailEndpoint: String

    fun fetchDeals(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchDeal(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchTopPaid(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchTopPaid(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchTopFree(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchTopFree(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchNew(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchNew(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchBestRated(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchBestRated(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchComingSoon(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchComingSoon(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchMostPlayed(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchMostPlayed(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchIndieGames(params: XboxApi.Params): List<String> {
        val response = xboxApi
            .fetchIndieGames(params.toQueryMap(moshi))
            .getItems()

        return response.map { it.Id }
    }

    fun fetchProducts(productIds: List<String>, market: String, language: String): List<Game> {
        val ids = productIds.joinToString(",")
        val response = xboxApi.fetchProducts(
            url = xboxProductDetailEndpoint,
            productIds = ids,
            market = market,
            language = "$language-$market"
        ).execute()

        val productMarketInputList = (response.body()?.Products ?: emptyList()).map {
            ProductToGameMapper.ProductMarketInput(
                product = it,
                market = market,
                language = language
            )
        }

        return gameMapper.map(productMarketInputList)
    }
}

fun Call<XboxListResponse>.getItems(): List<Item> {
    return execute()?.body()?.Items ?: emptyList()
}
