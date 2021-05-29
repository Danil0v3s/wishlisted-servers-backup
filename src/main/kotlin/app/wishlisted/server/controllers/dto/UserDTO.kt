package app.wishlisted.server.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDTO(
    val name: String?,
    val email: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String?,
    val token: String?
)
