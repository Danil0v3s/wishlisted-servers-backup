package app.wishlisted.server.data.model.xbox.product

data class Price(
    val CurrencyCode: String,
    val IsPIRequired: Boolean?,
    val ListPrice: Double,
    val MSRP: Double,
    val TaxType: String,
    val WholesaleCurrencyCode: String,
    val WholesalePrice: Double?
)
