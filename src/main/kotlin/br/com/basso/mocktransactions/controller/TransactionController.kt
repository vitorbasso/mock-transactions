package br.com.basso.mocktransactions.controller

import br.com.basso.mocktransactions.entity.Transaction
import br.com.basso.mocktransactions.enum.ErrorCode
import br.com.basso.mocktransactions.exception.CustomBadRequestException
import br.com.basso.mocktransactions.service.TransactionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Month

@RestController
@RequestMapping("{clientId}/transactions")
class TransactionController(
    val transactionService: TransactionService
) {

    @GetMapping("/{transactionYear}/{transactionMonth}")
    fun getTransactions(
        @PathVariable clientId: String,
        @PathVariable transactionYear: String,
        @PathVariable transactionMonth: String
    ): List<Transaction> {
        validateRequest(clientId, transactionYear, transactionMonth)
        return this.transactionService.getTransactions(
            clientId.toInt(),
            transactionYear.toInt(),
            transactionMonth.toInt()
        )
    }

    private fun validateRequest(clientId: String, transactionYear: String, transactionMonth: String) {
        when {
            clientId.toIntOrNull() ?: 0 !in idRange -> {
                ErrorCode.CODE_00
            }
            transactionYear.toIntOrNull() ?: 0 !in yearRange -> {
                ErrorCode.CODE_01
            }
            !Month.values().map { it.value }.contains(transactionMonth.toIntOrNull() ?: 0) -> {
                ErrorCode.CODE_02
            }
            else -> null
        }?.let { throw CustomBadRequestException(it) }
    }

    companion object {
        private val idRange = (1000..100000)
        private val yearRange = (1900..2021)
    }

}
