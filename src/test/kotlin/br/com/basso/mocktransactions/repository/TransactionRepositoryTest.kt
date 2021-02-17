package br.com.basso.mocktransactions.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class TransactionRepositoryTest {

    private val minValue = -9999999
    private val maxValue = 9999999
    private val minDescriptionSize = 10
    private val maxDescriptionSize = 60

    private val transactionRepository =
        TransactionRepository(minValue, maxValue, minDescriptionSize, maxDescriptionSize)

    @Test
    fun `assert that transactions values are between min and max values allowed`() {
        //when
        val userId = List(100) {
            Random.nextInt(1000, 100000)
        }
        val transactionYear = List(100) {
            Random.nextInt(1900, 2030)
        }
        val transactionMonth = List(100) {
            Random.nextInt(1, 12)
        }

        //when
        val transactions = List(100) { i ->
            transactionRepository.getTransactions(userId[i], transactionYear[i], transactionMonth[i])
        }

        //then
        assertThat(transactions).allSatisfy {
            assertThat(it).allSatisfy { trans ->
                assertThat(trans.valor).isBetween(minValue, maxValue)
            }
        }

    }

    @Test
    fun `assert that the size of the list of transactions returned is the first digit of id times month`() {
        //when
        val userId = List(100) {
            Random.nextInt(1000, 100000)
        }
        val transactionYear = List(100) {
            Random.nextInt(1900, 2030)
        }
        val transactionMonth = List(100) {
            Random.nextInt(1, 12)
        }

        //when
        val transactions = List(100) { i ->
            transactionRepository.getTransactions(userId[i], transactionYear[i], transactionMonth[i])
        }

        //then
        assertThat(transactions).allSatisfy {
            val index = transactions.indexOf(it)
            assertThat(it.size).isEqualTo(userId[index].toString().substring(0, 1).toInt() * transactionMonth[index])
        }

    }
}
