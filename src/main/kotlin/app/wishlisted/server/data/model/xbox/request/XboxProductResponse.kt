package app.wishlisted.server.data.model.xbox.request

import app.wishlisted.server.data.model.xbox.product.Product

data class XboxProductResponse(
    val Products: List<Product>
)
