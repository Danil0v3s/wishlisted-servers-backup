package app.wishlisted.server.data.repository

import app.wishlisted.server.data.model.Profile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProfileRepository : CrudRepository<Profile, UUID>
