package org.example.domain.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.example.domain.BaseEntity
import javax.persistence.*

/**
 * Represents application user's role - ADMIN, USER, etc.
 */

@Entity
data class Role(
    @Enumerated(EnumType.STRING)
    val name: UserRole,
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("roles")
    val users: List<Account>
): BaseEntity()

enum class UserRole(val short: String) {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN")
}