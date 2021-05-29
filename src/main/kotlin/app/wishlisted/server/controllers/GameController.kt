package app.wishlisted.server.controllers

import app.wishlisted.server.data.api.XboxApi
import app.wishlisted.server.data.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/games")
class GameController @Autowired constructor(
    private val gameService: GameService
) {

    @GetMapping("status")
    fun fetchStatues(): List<String> {
        return listOf("Deals", "TopPaid", "TopFree", "New", "BestRated", "ComingSoon", "MostPlayed", "Indie")
    }

    @GetMapping("deals")
    fun fetchDeals(params: XboxApi.Params) = gameService.fetchDeals(params)

    @GetMapping("topPaid")
    fun fetchTopPaid(params: XboxApi.Params) = gameService.fetchTopPaid(params)

    @GetMapping("topFree")
    fun fetchTopFree(params: XboxApi.Params) = gameService.fetchTopFree(params)

    @GetMapping("new")
    fun fetchNew(params: XboxApi.Params) = gameService.fetchNew(params)

    @GetMapping("bestRated")
    fun fetchBestRated(params: XboxApi.Params) = gameService.fetchBestRated(params)

    @GetMapping("comingSoon")
    fun fetchComingSoon(params: XboxApi.Params) = gameService.fetchComingSoon(params)

    @GetMapping("mostPlayed")
    fun fetchMostPlayed(params: XboxApi.Params) = gameService.fetchMostPlayed(params)

    @GetMapping("indie")
    fun fetchIndieGames(params: XboxApi.Params) = gameService.fetchIndieGames(params)

    @GetMapping("/details")
    fun fetchGames(
        @RequestParam("gameIds") gameIds: List<String>,
        @RequestParam("market") market: String,
        @RequestParam("language") language: String
    ) = gameService.fetchProducts(gameIds, market, language)
}
