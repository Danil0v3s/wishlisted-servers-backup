package app.wishlisted.server.core.configuration.security.filter

import app.wishlisted.server.core.configuration.ConfigProperties
import app.wishlisted.server.core.configuration.security.JwtManager
import app.wishlisted.server.data.repository.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenBasedAuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtManager: JwtManager,
    private val configProperties: ConfigProperties,
    private val userRepository: UserRepository
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (header != null && header.startsWith(configProperties.tokenPrefix)) {
            SecurityContextHolder.getContext().authentication = getAuthentication(request)
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        val email = jwtManager.decode(token.replace(configProperties.tokenPrefix, "").trim()) ?: return null
        val managedUser = userRepository.findByEmail(email) ?: return null
        return UsernamePasswordAuthenticationToken(managedUser, null, emptyList())
    }
}
