package org.example.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.example.exception.AuthenticationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    @Autowired
    private val userDetailsService: UserDetailsService,
    @Value("\${jwt.token.secret}")
    private var secret: String,
    @Value("\${jwt.token.expired}")
    private val expirationInMilliseconds: Long
) {
    @PostConstruct
    fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expirationInMilliseconds))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (ex: Exception) {
            when (ex) {
                is JwtException, is IndexOutOfBoundsException -> {
                    throw AuthenticationException("JWT token is expired or invalid")
                }
                else -> throw ex
            }
        }
    }

    fun getAuthentication(token: String, request: HttpServletRequest): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).also {
            it.details = WebAuthenticationDetailsSource().buildDetails(request)
        }
    }

    private fun getUsername(token: String?): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    }
}