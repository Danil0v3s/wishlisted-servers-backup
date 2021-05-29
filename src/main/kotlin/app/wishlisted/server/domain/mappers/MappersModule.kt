package app.wishlisted.server.domain.mappers

import app.wishlisted.server.data.model.Game
import app.wishlisted.server.domain.Mapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MappersModule {

    @Bean
    fun provideProductToGameMapper(): Mapper<ProductToGameMapper.ProductMarketInput, Game> {
        return ProductToGameMapper()
    }
}
