package app.wishlisted.server.core.configuration.oauth

import app.wishlisted.server.data.model.User
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

private const val GRAPH_API_URL = "https://graph.facebook.com/v2.12/me"
private val FIELDS = listOf("name", "email", "picture.width(400).height(400)")

class FacebookProvider(token: String) : ApiBinding(token) {
    fun auth(): User? {
        val restTemplate = RestTemplate()
        val uriBuilder = UriComponentsBuilder
            .fromHttpUrl(GRAPH_API_URL)
            .queryParam("fields", java.lang.String.join(",", FIELDS))
        restTemplate.interceptors = authInterceptors
        val userEntity = restTemplate.getForEntity(uriBuilder.toUriString(), User::class.java)
        return userEntity.body
    }
}
