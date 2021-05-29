package app.wishlisted.server

import app.wishlisted.server.core.configuration.ConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties::class)
class ServerApplication {

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
