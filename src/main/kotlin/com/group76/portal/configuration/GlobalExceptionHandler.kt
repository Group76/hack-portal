package com.group76.portal.configuration

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    protected fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException, headers: HttpHeaders?,
        status: HttpStatus?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, List<String?>> = HashMap()

        val errors = ex.bindingResult
            .fieldErrors
            .stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.toList())

        body["errors"] = errors

        return ResponseEntity<Any>(body, HttpStatus.BAD_REQUEST)
    }
}