package app.wishlisted.server.data.service

import app.wishlisted.server.data.model.User
import app.wishlisted.server.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) : UserDetailsService {
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun findById(id: UUID): User? {
        return userRepository.findById(id).get()
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw UsernameNotFoundException(username)
        return SpringUser(user.email, user.password, emptyList())
    }
}
