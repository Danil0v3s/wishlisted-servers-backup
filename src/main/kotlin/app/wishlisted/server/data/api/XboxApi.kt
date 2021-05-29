package app.wishlisted.server.data.api

import app.wishlisted.server.data.model.xbox.request.XboxListResponse
import app.wishlisted.server.data.model.xbox.request.XboxProductResponse
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface XboxApi {

    data class Params(
        val market: String = "br",
        val language: String = "pt",
        val itemTypes: String = "Game",
        val deviceFamily: String = "Windows.Xbox",
        val count: Int = 20,
        val skipItems: Int = 0,
        val gameCapabilities: String? = null,
        val numberOfPlayers: String? = null
    ) {
        fun toQueryMap(moshi: Moshi): Map<String, String> {
            val adapter = moshi.adapter(Params::class.java)
            return adapter.toJsonValue(this) as Map<String, String>
        }
    }

    @GET("Computed/TopPaid")
    fun fetchTopPaid(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/TopFree")
    fun fetchTopFree(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/New")
    fun fetchNew(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/BestRated")
    fun fetchBestRated(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/ComingSoon")
    fun fetchComingSoon(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/Deal")
    fun fetchDeal(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Computed/MostPlayed")
    fun fetchMostPlayed(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET("Collection/XboxIndieGames")
    fun fetchIndieGames(@QueryMap query: Map<String, String>): Call<XboxListResponse>

    @GET
    fun fetchProducts(
        @Url url: String,
        @Query("bigIds") productIds: String,
        @Query("market") market: String,
        @Query("languages") language: String,
        @Query("MS-CV") signature: String = "DGU1mcuYo0WMMp+F.1"
    ): Call<XboxProductResponse>
}
