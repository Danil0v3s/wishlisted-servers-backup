package app.wishlisted.server.core.configuration.security

import app.wishlisted.server.core.configuration.ConfigProperties
import app.wishlisted.server.core.configuration.security.filter.FacebookAuthenticationFilter
import app.wishlisted.server.core.configuration.security.filter.GoogleAuthenticationFilter
import app.wishlisted.server.core.configuration.security.filter.TokenBasedAuthorizationFilter
import app.wishlisted.server.data.repository.UserRepository
import app.wishlisted.server.data.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val jwtManager: JwtManager,
    private val userService: UserService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val authenticationProvider: CustomAuthenticationProvider,
    private val configProperties: ConfigProperties,
    private val userRepository: UserRepository
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun provideAuthManager(): AuthenticationManager = authenticationManager()

    override fun configure(http: HttpSecurity) {
        http
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, configProperties.signUpUrl, configProperties.signinUrl).permitAll()
            .antMatchers(HttpMethod.GET, "/**/games/**").permitAll()
            .and().authorizeRequests().anyRequest().authenticated()
            .and()
            .addFilter(TokenBasedAuthorizationFilter(authenticationManager(), jwtManager, configProperties, userRepository))
            .addFilterBefore(FacebookAuthenticationFilter(authenticationManager(), jwtManager, userService, configProperties), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(GoogleAuthenticationFilter(authenticationManager(), jwtManager, userService, configProperties), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .exceptionHandling()
            .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .authenticationProvider(authenticationProvider)
            .userDetailsService(userService)
            .passwordEncoder(bCryptPasswordEncoder)
    }
}
