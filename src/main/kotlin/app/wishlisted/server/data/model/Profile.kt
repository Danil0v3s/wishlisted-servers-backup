package app.wishlisted.server.data.model

import com.fasterxml.jackson.annotation.JsonBackReference
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
    name = "tb_user_profile",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["fiscalNumber"])
    ]
)
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var phone: Long,
    var phonePrefix: Int,
    var fiscalNumber: String,
    var zipCode: String,
    var addressNumber: String,
    var street: String,
    var city: String,
    var addressState: String,
    var district: String,
    var complement: String? = null,
    @JsonBackReference
    @OneToOne
    var user: User? = null,
)
