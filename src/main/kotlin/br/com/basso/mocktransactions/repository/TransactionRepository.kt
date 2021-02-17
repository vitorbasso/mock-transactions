package br.com.basso.mocktransactions.repository

import br.com.basso.mocktransactions.entity.Transaction
import br.com.basso.mocktransactions.util.RandomUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TransactionRepository(
    @Value("\${min-transaction-value:-9999999}")
    private val minValue: Int,
    @Value("\${max-transaction-value:9999999}")
    private val maxValue: Int,
    @Value("\${min-description-size:10}")
    private val minDescriptionSize: Int,
    @Value("\${max-description-size:60}")
    private val maxDescriptionSize: Int
) {

    fun getTransactions(
        userId: Int,
        transactionYear: Int,
        transactionMonth: Int
    ): List<Transaction> {
        val random = RandomUtil.getInstance(userId, transactionYear, transactionMonth)
        val numberOfTransactions = userId.toString().substring(0, 1).toInt() * transactionMonth
        return List(numberOfTransactions) { index ->
            getRandomTransaction(
                index = index,
                userId = userId,
                transactionMonth = transactionMonth,
                random = random
            )
        }
    }

    private fun getRandomTransaction(
        index: Int,
        userId: Int,
        transactionMonth: Int,
        random: RandomUtil
    ) = Transaction(
        descricao = random.getRandomDescription(minDescriptionSize, maxDescriptionSize),
        data = random.getRandomTimestamp().time,
        valor = ((userId % (index + 1)) * transactionMonth).let {
            random.getRandomValue(
                minValue + it,
                maxValue - it
            ) + it
        }
    )

}
