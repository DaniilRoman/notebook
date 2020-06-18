package org.example.security

import org.example.domain.response.TokenResponse
import org.example.domain.user.Account
import org.example.service.AccountService
import org.example.utils.LoggerDelegate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val accountService: AccountService,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder
) {
    val log by LoggerDelegate()

    fun encode(password: String): String {
        return encoder.encode(password)
    }

    fun login(accountForLogin: Account): TokenResponse = with(accountForLogin) {
        val account = accountService.getByUsername(username)

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val token: String = tokenProvider.createToken(username)
        val authorities: List<SimpleGrantedAuthority> = account.roles!!.map { SimpleGrantedAuthority(it.name.name) }

        log.info("User $username has been login.")
        return TokenResponse(token, username, authorities)
    }
}