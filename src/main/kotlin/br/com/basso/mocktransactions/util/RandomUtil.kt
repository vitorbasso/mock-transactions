package br.com.basso.mocktransactions.util

import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import kotlin.random.Random

class RandomUtil private constructor(userId: Int, private val transactionYear: Int, private val transactionMonth: Int) {

    //generates a Random object with a seed number that is statistically improbable to be repeated given different
    //values for a, b and c
    private val seed = (userId * BIGGER_PRIME_NUMBER + transactionYear) * BIG_PRIME_NUMBER + transactionMonth

    private val random = Random(seed)

    fun getRandomTimestamp(): Timestamp = Timestamp.valueOf(
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
    )

    fun getRandomValue(start: Int, end: Int) = random.nextInt(start, end)

    fun getRandomDescription(minDescriptionSize: Int, maxDescriptionSize: Int) =
        List(maxDescriptionSize / MIN_WORD_SIZE) {
            getRandomWord(getRandomValue(MIN_WORD_SIZE, MAX_WORD_SIZE))
        }.joinToString(separator = " ")
            .substring(0, getRandomValue(minDescriptionSize + 1, maxDescriptionSize))
            .trim()
            .capitalize()

    private fun getRandomWord(wordSize: Int): String {
        var isVowelTurn = random.nextBoolean()
        return List(wordSize) {
            isVowelTurn = !isVowelTurn
            if (isVowelTurn) VOWEL_LIST[random.nextInt(0, VOWEL_LIST.size)]
            else CONSONANT_LIST[random.nextInt(0, CONSONANT_LIST.size)]
        }.joinToString(separator = "")
    }

    companion object {
        fun getInstance(a: Int, b: Int, c: Int) = RandomUtil(a, b, c)
        private const val BIG_PRIME_NUMBER = 31L
        private const val BIGGER_PRIME_NUMBER = 79L
        private const val MIN_WORD_SIZE = 2
        private const val MAX_WORD_SIZE = 12
        private val RANGE_TIME = Pair(0, 60)
        private val RANGE_HOUR = Pair(0, 24)
        private val VOWEL_LIST = listOf('a', 'e', 'i', 'o', 'u')
        private val CONSONANT_LIST = CharRange('a', 'z').filter { !VOWEL_LIST.contains(it) }
    }
}
