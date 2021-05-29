package app.wishlisted.server.controllers

import app.wishlisted.server.controllers.dto.ProfileDTO
import app.wishlisted.server.controllers.dto.UserDTO
import app.wishlisted.server.data.model.User
import app.wishlisted.server.data.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/account")
class AccountController @Autowired constructor(
    private val accountService: AccountService
) {
    @GetMapping("/profile/me")
    fun fetchProfile() = accountService.fetchProfile()

    @PutMapping("/profile")
    fun createProfile(@RequestBody profile: ProfileDTO) = accountService.createProfile(profile)

    @PostMapping("/sign-in")
    fun authenticate(@RequestBody user: UserDTO) = accountService.authenticate(user)

    @PostMapping("/sign-up")
    fun register(@RequestBody user: User) = accountService.register(user)
}
