package br.com.basso.mocktransactions.handler

import br.com.basso.mocktransactions.dto.ApiErrorDto
import br.com.basso.mocktransactions.exception.CustomMockException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice(
    private val messageSource: MessageSource
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomMockException::class)
    fun handleCustomMockException(ex: CustomMockException) =
        ApiErrorDto(code = ex.code, cause = getLocalizedMessage(ex.message))

    private fun getLocalizedMessage(cause: String) =
        this.messageSource.getMessage(cause, null, LocaleContextHolder.getLocale())

}
