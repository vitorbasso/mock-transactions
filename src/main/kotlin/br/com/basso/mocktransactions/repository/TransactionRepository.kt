package br.com.basso.mocktransactions.repository

import br.com.basso.mocktransactions.entity.Transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import kotlin.random.Random

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
        val random = getRandomObject(userId, transactionYear, transactionMonth)
        return listOf(
            Transaction(
                descricao = getRandomDescription(random),
                data = getRandomDate(
                    transactionYear = transactionYear,
                    transactionMonth = transactionMonth,
                    random = random
                ),
                valor = getRandomValue(random)
            )
        )
    }

    private fun getRandomDescription(random: Random): String {
        val wordList = mutableListOf<String>()
        var phraseSpaceLeft = random.nextInt(minDescriptionSize, maxDescriptionSize + 1)
        while (phraseSpaceLeft > MIN_WORD_SIZE) {
            random.nextInt(MIN_WORD_SIZE, MAX_WORD_SIZE).also { wordSize ->
                wordList.add(getRandomWord(wordSize, random))
                phraseSpaceLeft -= wordSize
            }
        }
        return wordList.joinToString(separator = " ", limit = maxDescriptionSize)
    }

    private fun getRandomWord(wordSize: Int, random: Random): String {
        var isVowelTurn = random.nextBoolean()
        var word = ""
        for (i in 0..wordSize) {
            word += if (isVowelTurn) VOWEL_LIST[random.nextInt(0, VOWEL_LIST.size)]
            else CONSONANT_LIST[random.nextInt(0, CONSONANT_LIST.size)]
            isVowelTurn = !isVowelTurn
        }
        return word
    }

    private fun getRandomValue(random: Random) = random.nextInt(minValue, maxValue)

    private fun getRandomDate(
        transactionYear: Int,
        transactionMonth: Int,
        random: Random
    ) = Timestamp.valueOf(
        LocalDate.of(
            transactionYear,
            transactionMonth,
            random.nextInt(1, YearMonth.of(transactionYear, transactionMonth).lengthOfMonth() + 1)
        ).atTime(
            LocalTime.of(
                random.nextInt(RANGE_TIME.first, RANGE_HOUR.second),
                random.nextInt(RANGE_TIME.first, RANGE_TIME.second),
                random.nextInt(RANGE_TIME.first, RANGE_TIME.second)
            )
        )
    ).time

    //generates a Random object with a seed number that is statistically improbable to be repeated given different
    //values for a, b and c
    private fun getRandomObject(a: Int, b: Int, c: Int) = Random((a * BIGGER_PRIME_NUMBER + b) * BIG_PRIME_NUMBER + c)

    companion object {
        private const val BIG_PRIME_NUMBER = 31L
        private const val BIGGER_PRIME_NUMBER = 79L
        private const val MIN_WORD_SIZE = 2
        private const val MAX_WORD_SIZE = 14
        private val RANGE_TIME = Pair(0, 60)
        private val RANGE_HOUR = Pair(0, 24)
        private val VOWEL_LIST = listOf('a', 'e', 'i', 'o', 'u')
        private val CONSONANT_LIST = CharRange('a', 'z').filter { !VOWEL_LIST.contains(it) }
    }

}
