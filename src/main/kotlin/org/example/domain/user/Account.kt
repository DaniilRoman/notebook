package org.example.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.example.domain.BaseEntity
import org.example.domain.request.AccountRequest
import javax.persistence.*

/**
 * Represents application user.
 */

@Entity
data class Account(
    val username: String,
    @JsonIgnore
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
                joinColumns = [
                    JoinColumn(name="account_id", referencedColumnName = "id")
                ],
                inverseJoinColumns = [
                    JoinColumn(name="role_id", referencedColumnName = "id")
                ])
    @JsonIgnoreProperties("users")
    val roles: Set<Role>? = null
): BaseEntity() {
    constructor(accountRequest: AccountRequest): this(accountRequest.username, accountRequest.password)
}
