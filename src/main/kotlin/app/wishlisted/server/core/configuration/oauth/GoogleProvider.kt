package app.wishlisted.server.core.configuration.oauth

import app.wishlisted.server.data.model.User
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

private const val GRAPH_API_URL = "https://www.googleapis.com/oauth2/v3/userinfo"

class GoogleProvider(token: String) : ApiBinding(token) {
    fun auth(): User? {
        val restTemplate = RestTemplate()
        val uriBuilder = UriComponentsBuilder
            .fromHttpUrl(GRAPH_API_URL)
        restTemplate.interceptors = authInterceptors
        val userEntity = restTemplate.getForEntity(uriBuilder.toUriString(), User::class.java)
        return userEntity.body
    }
}
