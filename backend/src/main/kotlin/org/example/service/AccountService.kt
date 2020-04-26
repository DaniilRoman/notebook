package org.example.service

import org.example.domain.user.Account
import org.example.domain.user.UserRole
import java.util.*

interface AccountService {
    fun getAll(): List<Account>

    fun getById(id: UUID): Account

    fun getByUsername(username: String): Account

    fun create(account: Account, role: UserRole): Account

    fun delete(id: UUID)

//    fun update(account: Account): Account
}