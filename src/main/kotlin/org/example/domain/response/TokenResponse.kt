package org.example.domain.response

import org.example.utils.HEADER_PREFIX
import org.springframework.security.core.GrantedAuthority

data class TokenResponse(val token: String, val username: String, val authorities: List<GrantedAuthority>) {
    val type = HEADER_PREFIX
}