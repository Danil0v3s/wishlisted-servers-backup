package app.wishlisted.server.controllers.dto

data class ProfileDTO(
    val id: String? = null,
    var phone: Long,
    var phonePrefix: Int,
    var fiscalNumber: String,
    var zipCode: String,
    var addressNumber: String,
    var street: String,
    var city: String,
    var addressState: String,
    var district: String,
    var complement: String,
    var abilities: Set<String> = emptySet()
)
