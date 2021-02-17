package br.com.basso.mocktransactions.repository

import br.com.basso.mocktransactions.entity.Transaction
import br.com.basso.mocktransactions.util.RandomUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TransactionRepository(
    @Value("\${minTransactionValue:-9999999}")
    private val minValue: Int,
    @Value("\${maxTransactionValue:9999999}")
    private val maxValue: Int,
    @Value("\${minDescriptionSize:10}")
    private val minDescriptionSize: Int,
    @Value("\${maxDescriptionSize:60}")
    private val maxDescriptionSize: Int
) {

    fun getTransactions(userId: Int, transactionYear: Int, transactionMonth: Int): List<Transaction> {
        val random = RandomUtil.getInstance(userId, transactionYear, transactionMonth)
        val numberOfTransactions = userId.toString().substring(0,1).toInt() * transactionMonth
        return List(numberOfTransactions) {
            getRandomTransaction(random)
        }
    }

    private fun getRandomTransaction(random: RandomUtil) = Transaction(
        descricao = random.getRandomDescription(minDescriptionSize, maxDescriptionSize),
        data = random.getRandomDate(),
        valor = random.getRandomValue(minValue, maxValue)
    )

}
