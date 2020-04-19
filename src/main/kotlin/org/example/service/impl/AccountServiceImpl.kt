package org.example.service.impl

import org.example.domain.user.Account
import org.example.repository.AccountRepository
import org.example.service.AccountService
import org.example.utils.unwrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl: AccountService {

    @Autowired
    private lateinit var repository: AccountRepository

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun getById(id: UUID): Account? {
        return repository.findById(id).unwrap()
    }

    override fun save(account: Account): Account {
        return repository.save(
            Account(account.username, account.password, account.roles))
    }

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

    override fun update(account: Account): Account {
        return repository.save(account)
    }

}
