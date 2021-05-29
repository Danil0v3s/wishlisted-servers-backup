package app.wishlisted.server.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "tb_user",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["email"])
    ]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    var name: String?,
    var email: String,
    @OneToOne
    var profile: Profile? = null
)
