package org.example.service.impl

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.example.exception.UserAlreadyExistsException
import org.example.exception.NotFoundException
import org.example.domain.user.Account
import org.example.domain.user.UserRole
import org.example.repository.AccountRepository
import org.example.repository.RoleRepository
import org.example.service.AccountService
import org.example.utils.unwrap
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(
    private val accountRepo: AccountRepository,
    private val roleRepo: RoleRepository
) : AccountService {

    companion object {
        val log: Logger = LogManager.getLogger(AccountService::class.java)
    }

    override fun getAll(): List<Account> {
        return accountRepo.findAll().apply {
            log.debug("Get all accounts.")
        }
    }

    override fun getById(id: UUID): Account {
        return accountRepo.findById(id).unwrap()
            ?: throw NotFoundException("User with id: `$id` not found")
    }

    override fun getByUsername(username: String): Account {
        return accountRepo.findByUsername(username)
            ?: throw NotFoundException("User `$username` not found")
    }

    override fun create(account: Account, role: UserRole): Account = with(account) {
        accountRepo.findByUsername(username)?.let {
            throw UserAlreadyExistsException("Creating user `$username` already exists.")
        }

        val userRole = roleRepo.getByName(role)
        return accountRepo.save(
            Account(
                username,
                password,
                setOf(userRole)
            )
        ).apply {
            log.info("New user `$username` has been created")
        }
    }

    override fun delete(id: UUID) {
        accountRepo.deleteById(id)
    }

//    override fun update(account: Account): Account {
//        return accountRepo.save(account)
//    }

}
