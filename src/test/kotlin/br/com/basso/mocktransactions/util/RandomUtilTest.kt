package br.com.basso.mocktransactions.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import java.time.LocalDate

internal class RandomUtilTest {

    private val id = 1000
    private val year = 2020
    private val month = 1

    private val randomUtil = RandomUtil.getInstance(id, year, month)

    @Test
    fun `assert that the timestamp returned is a random day inside the year and month provided`() {
        //given
        val month = LocalDate.of(year, month, 1)

        //when
        val returned = List(100) { randomUtil.getRandomTimestamp() }

        //then
        assertThat(returned).allSatisfy {
            it.toLocalDate().isAfter(month) && it.toLocalDate().isBefore(month.atEndOfMonth())
        }
    }

    @Test
    fun `assert that the value returned is a value between the two values provided`() {
        //given
        val minValue = 0
        val maxValue = 10

        //when
        val listOfValues = List(100) {
            randomUtil.getRandomValue(minValue, maxValue)
        }

        //then
        assertThat(listOfValues).allSatisfy { it in minValue..maxValue }
    }

    @Test
    fun `assert that the random description length is between the two values provided`() {
        //given
        val minDescriptionSize = 10
        val maxDescriptionSize = 60

        //when
        val listOfDescriptions = List(100) {
            randomUtil.getRandomDescription(minDescriptionSize, maxDescriptionSize)
        }

        //then
        assertThat(listOfDescriptions).allSatisfy { it.length in minDescriptionSize..maxDescriptionSize }
    }

}

private fun Timestamp.toLocalDate() = this.toLocalDateTime().toLocalDate()

private fun LocalDate.atEndOfMonth() = this.withDayOfMonth(this.lengthOfMonth())
