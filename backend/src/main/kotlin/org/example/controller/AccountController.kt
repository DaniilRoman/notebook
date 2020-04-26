package org.example.controller

import org.example.domain.request.AccountRequest
import org.example.domain.user.Account
import org.example.domain.user.UserRole
import org.example.security.AuthService
import org.example.service.AccountService
import org.example.utils.API_ACCOUNT_PREFIX
import org.example.utils.ID_PATH
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(API_ACCOUNT_PREFIX)
class AccountController(
    private val accountService: AccountService,
    private val authService: AuthService
) {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun getAll() = accountService.getAll()

    @GetMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    fun getById(@PathVariable id: UUID) = accountService.getById(id)

    @PostMapping("/sign_up")
    fun singUp(@RequestBody account: AccountRequest) = createAccount(account, UserRole.ROLE_USER)

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun createAdmin(@RequestBody account: AccountRequest) = createAccount(account, UserRole.ROLE_ADMIN)

    private fun createAccount(account: AccountRequest, role: UserRole) = with(account) {
        accountService.create(Account(
            AccountRequest(username, authService.encode(password))),
            role
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody account: AccountRequest) = authService.login(Account(account))

//    @PutMapping
//    fun update(@RequestBody notebook: Notebook) = accountService.update(notebook)

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID) = accountService.delete(id)

}