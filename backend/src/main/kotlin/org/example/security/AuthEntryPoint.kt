package org.example.security

import org.example.utils.LoggerDelegate
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint : AuthenticationEntryPoint {

    val log by LoggerDelegate()

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          ex: AuthenticationException
    ) {
        log.error("Unauthorized error. Message - {}", ex.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials")
    }
}