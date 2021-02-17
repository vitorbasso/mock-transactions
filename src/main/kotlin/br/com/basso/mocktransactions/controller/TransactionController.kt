package br.com.basso.mocktransactions.controller

import br.com.basso.mocktransactions.service.TransactionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("{clientId}/transactions")
class TransactionController(
    val transactionService: TransactionService
) {

    @GetMapping("/{transactionYear}/{transactionMonth}")
    fun getTransactions(
        @PathVariable clientId: Int,
        @PathVariable transactionYear: Int,
        @PathVariable transactionMonth: Int
    ) = this.transactionService.getTransactions(clientId, transactionYear, transactionMonth)

}
