package org.example.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class NotebookException(val msg: String): RuntimeException() {
    abstract val code: HttpStatus

    fun toResponse(): NotebookExceptionResponse {
        return NotebookExceptionResponse(code, msg)
    }
}

data class NotebookExceptionResponse(val code: HttpStatus, val message: String)

class AuthenticationException(msg: String, override val code: HttpStatus = HttpStatus.FORBIDDEN): NotebookException(msg)

class NotFoundException(msg: String, override val code: HttpStatus = HttpStatus.NOT_FOUND): NotebookException(msg)

class UserAlreadyExistsException(msg: String, override val code: HttpStatus = HttpStatus.CONFLICT): NotebookException(msg)

