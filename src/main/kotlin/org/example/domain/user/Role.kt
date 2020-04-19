package org.example.domain.user

import org.example.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany

/**
 * Represents application user's role - ADMIN, USER, etc.
 */

@Entity
class Role(
    val name: String,
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    val users: List<Account>
): BaseEntity()