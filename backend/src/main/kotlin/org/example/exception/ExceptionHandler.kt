package org.example.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHandler
{
    @ExceptionHandler(NotebookException::class)
    @ResponseBody
    fun onException(ex: NotebookException): ResponseEntity<NotebookExceptionResponse>
    {
        return ResponseEntity(ex.toResponse(), HttpStatus.BAD_REQUEST)
    }
}