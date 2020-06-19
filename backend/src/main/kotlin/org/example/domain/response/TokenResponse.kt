package org.example.domain.response

import org.example.utils.HEADER_PREFIX
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class TokenResponse(val token: String, val username: String, val authorities: List<Any>) {
    val type = HEADER_PREFIX
}