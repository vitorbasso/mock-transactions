package br.com.basso.mocktransactions.controller

import br.com.basso.mocktransactions.entity.Transaction
import br.com.basso.mocktransactions.exception.CustomBadRequestException
import br.com.basso.mocktransactions.service.TransactionService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class TransactionControllerTest {
    @RelaxedMockK
    lateinit var transactionService: TransactionService

    @InjectMockKs
    lateinit var transactionController: TransactionController

    @Test
    fun `assert that wrong id throws a CustomBadRequestException` () {
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("999", "2020", "12")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("100001", "2020", "12")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("adfasdf", "2020", "12")
        }
    }

    @Test
    fun `assert that wrong transactionYear throws a CustomBadRequestException` () {
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "1899", "12")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "2031", "12")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "asdfasd", "12")
        }
    }

    @Test
    fun `assert that wrong transactionMonth throws a CustomBadRequestException` () {
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "2020", "0")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "2020", "13")
        }
        assertThrows<CustomBadRequestException> {
            transactionController.getTransactions("10000", "2020", "adsfasdf")
        }
    }

    @Test
    fun `assert that getTransactions with correct parameters returns a list of transactions`() {
        //given
        val id = "1000"
        val year = "2020"
        val month = "12"

        every { transactionService.getTransactions(id.toInt(), year.toInt(), month.toInt()) } returns listOf(
            Transaction("asdf", 1234, 1234),
            Transaction("asdf", 1234, 1234),
            Transaction("asdf", 1234, 1234),
        )

        //when
        val returned = this.transactionController.getTransactions(id, year, month)

        //then
        assertThat(returned).allSatisfy {
            assertThat(it).isInstanceOf(Transaction::class.java)
        }
        assertThat(returned.size).isEqualTo(3)
    }
}