package org.example.service

import org.example.domain.user.Account
import java.util.*

interface AccountService {
    fun getAll(): List<Account>

    fun getById(id: UUID): Account?

    fun save(account: Account): Account

    fun delete(id: UUID)

    fun update(account: Account): Account
}