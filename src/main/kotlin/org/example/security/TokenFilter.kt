package org.example.security

import org.apache.logging.log4j.LogManager
import org.example.utils.HEADER_PREFIX
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter(
    @Autowired
    private val tokenProvider: TokenProvider
) : OncePerRequestFilter() {

    companion object {
        private val log = LogManager.getLogger(TokenFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJwt(request)
            if (jwt != null && tokenProvider.validateToken(jwt)) {
                val auth = tokenProvider.getAuthentication(jwt, request)

                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e: Exception) {
            log.error("Can NOT set user authentication -> Message: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        return if (authHeader != null && authHeader.startsWith(HEADER_PREFIX)) {
            authHeader.replace(HEADER_PREFIX, "")
        } else null
    }

}