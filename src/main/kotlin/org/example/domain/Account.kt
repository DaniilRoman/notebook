package org.example.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Account(
    @Id
    val id: UUID,
    val username: String,
    val password: String
)
