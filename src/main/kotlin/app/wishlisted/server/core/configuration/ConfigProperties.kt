package app.wishlisted.server.core.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "wishlisted")
data class ConfigProperties(
    var tokenSecret: String = "",
    var tokenLifeTime: Long = 0L,
    var tokenPrefix: String = "",
    var facebookLoginUrl: String = "",
    var googleLoginUrl: String = "",
    var signinUrl: String = "",
    var signUpUrl: String = ""
)
