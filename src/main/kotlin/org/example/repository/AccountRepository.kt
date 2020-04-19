package org.example.repository

import org.example.domain.user.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, UUID> {
    fun getByUsername(username: String): Account?
}