package app.wishlisted.server.core.configuration.security

import app.wishlisted.server.data.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userService: UserService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication? {
        val email = authentication.name
        val password = authentication.credentials.toString()

        val user = userService.findByEmail(email) ?: return null
        if (bCryptPasswordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(user, null, listOf())
        }

        return null
    }

    override fun supports(authentication: Class<*>) =
        authentication == UsernamePasswordAuthenticationToken::class.java
}
