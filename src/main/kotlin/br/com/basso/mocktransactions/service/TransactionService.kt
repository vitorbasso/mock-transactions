package br.com.basso.mocktransactions.service

import br.com.basso.mocktransactions.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun getTransactions(clientId: Int, transactionYear: Int, transactionMonth: Int) =
        this.transactionRepository.getTransactions(clientId, transactionYear, transactionMonth)

}
