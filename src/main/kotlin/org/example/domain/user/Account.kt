package org.example.domain.user

import org.example.domain.BaseEntity
import javax.persistence.*

/**
 * Represents application user.
 */

@Entity
data class Account(
    val username: String,
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
                joinColumns = [
                    JoinColumn(name="account_id", referencedColumnName = "id")
                ],
                inverseJoinColumns = [
                    JoinColumn(name="role_id", referencedColumnName = "id")
                ])
    val roles: List<Role>
): BaseEntity()
