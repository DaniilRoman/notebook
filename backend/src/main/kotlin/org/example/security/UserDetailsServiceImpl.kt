package org.example.security

import org.example.service.AccountService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val accountService: AccountService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = accountService.getByUsername(username)

        val authorities = user.roles!!.map { role -> SimpleGrantedAuthority(role.name.name) }

        return User
            .withUsername(username)
            .password(user.password)
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}