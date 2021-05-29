package app.wishlisted.server.core.configuration.security.filter

import app.wishlisted.server.core.configuration.ConfigProperties
import app.wishlisted.server.core.configuration.oauth.GoogleProvider
import app.wishlisted.server.core.configuration.security.JwtManager
import app.wishlisted.server.data.model.User
import app.wishlisted.server.data.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class GoogleAuthenticationFilter(
    authenticationManager: AuthenticationManager?,
    private val jwtManager: JwtManager,
    private val userService: UserService,
    configProperties: ConfigProperties
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher(configProperties.googleLoginUrl)
) {

    init {
        super.setAuthenticationManager(authenticationManager)
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        val accessToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (accessToken != null && !accessToken.isEmpty()) {
            val provider = GoogleProvider(accessToken)
            val user = provider.auth()
            val managedUser = getAuthentication(user)
            if (managedUser != null) return managedUser
        }
        return null
    }

    private fun getAuthentication(user: User?): Authentication? {
        if (user != null) {
            var managedUser: User? = userService.findByEmail(user.email)
            if (managedUser == null) {
                managedUser = userService.save(user)
            }
            return UsernamePasswordAuthenticationToken(managedUser, null, emptyList())
        }
        return null
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val user = authResult.principal as User
        val token = jwtManager.encode(user.email)
        response.addHeader(HttpHeaders.SET_COOKIE, token)
    }
}
