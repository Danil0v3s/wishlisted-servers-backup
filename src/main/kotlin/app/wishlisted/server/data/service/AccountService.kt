package app.wishlisted.server.data.service

import app.wishlisted.server.controllers.dto.ProfileDTO
import app.wishlisted.server.controllers.dto.UserDTO
import app.wishlisted.server.core.configuration.security.JwtManager
import app.wishlisted.server.data.model.Profile
import app.wishlisted.server.data.model.User
import app.wishlisted.server.data.repository.ProfileRepository
import app.wishlisted.server.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService @Autowired constructor(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtManager: JwtManager,
) {

    fun fetchProfile(): User? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        val user = authentication.principal as? User ?: return null
        return userRepository.findByEmail(user.email)
    }

    fun authenticate(user: UserDTO): UserDTO {
        val managedUser = userRepository.findByEmail(user.email) ?: throw IllegalArgumentException("User not found")

        return if (bCryptPasswordEncoder.matches(user.password, managedUser.password)) {
            UserDTO(managedUser.name, managedUser.email, token = jwtManager.encode(managedUser.email), password = null)
        } else {
            throw IllegalArgumentException("Invalid password")
        }
    }

    fun register(user: User): UserDTO {
        userRepository.save(user.apply { password = bCryptPasswordEncoder.encode(user.password) })

        return UserDTO(user.name, user.email, token = jwtManager.encode(user.email), password = null)
    }

    fun createProfile(profileDTO: ProfileDTO): User? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        val managedUser = authentication.principal as? User ?: return null

        val profile = Profile(
            phone = profileDTO.phone ?: throw IllegalArgumentException(),
            addressNumber = profileDTO.addressNumber ?: throw IllegalArgumentException(),
            addressState = profileDTO.addressState ?: throw IllegalArgumentException(),
            city = profileDTO.city ?: throw IllegalArgumentException(),
            complement = profileDTO.complement ?: throw IllegalArgumentException(),
            district = profileDTO.district ?: throw IllegalArgumentException(),
            fiscalNumber = profileDTO.fiscalNumber ?: throw IllegalArgumentException(),
            phonePrefix = profileDTO.phonePrefix ?: throw IllegalArgumentException(),
            street = profileDTO.street ?: throw IllegalArgumentException(),
            zipCode = profileDTO.zipCode ?: throw IllegalArgumentException(),
            user = managedUser,
        )

        val managedProfile = profileRepository.save(profile.apply { this.user = managedUser })

        return userRepository.save(managedUser.apply { this.profile = managedProfile })
    }
}
