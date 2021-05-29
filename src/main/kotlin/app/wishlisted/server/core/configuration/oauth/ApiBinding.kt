package app.wishlisted.server.core.configuration.oauth

import app.wishlisted.server.core.configuration.security.HeaderRequestInterceptor
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestInterceptor

open class ApiBinding protected constructor(
    private var token: String
) {
    protected val authInterceptors: List<ClientHttpRequestInterceptor>
        get() {
            val interceptors: MutableList<ClientHttpRequestInterceptor> = ArrayList()
            interceptors.add(HeaderRequestInterceptor(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
            interceptors.add(HeaderRequestInterceptor(HttpHeaders.AUTHORIZATION, token))
            return interceptors
        }
}
