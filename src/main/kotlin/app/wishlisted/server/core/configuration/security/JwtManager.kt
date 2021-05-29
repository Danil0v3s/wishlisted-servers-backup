package app.wishlisted.server.core.configuration.security

import app.wishlisted.server.core.configuration.ConfigProperties
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtManager @Autowired constructor(
    private val configProperties: ConfigProperties
) {
    fun decode(token: String): String? {
        return JWT.require(Algorithm.HMAC512(configProperties.tokenSecret))
            .build()
            .verify(token)
            .subject
    }

    fun encode(subject: String): String {
        val expirationTime = Date(
            System.currentTimeMillis() + configProperties.tokenLifeTime
        )
        return JWT.create()
            .withSubject(subject)
            .withExpiresAt(expirationTime)
            .sign(Algorithm.HMAC512(configProperties.tokenSecret))
    }
}
