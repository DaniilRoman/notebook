package org.example.security

import org.apache.logging.log4j.LogManager
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint : AuthenticationEntryPoint {
    companion object {
        private val log = LogManager.getLogger(AuthEntryPoint::class.java)
    }

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          ex: AuthenticationException
    ) {
        log.error("Unauthorized error. Message - {}", ex.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials")
    }
}