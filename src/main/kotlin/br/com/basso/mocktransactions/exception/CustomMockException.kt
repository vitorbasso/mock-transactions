package br.com.basso.mocktransactions.exception

open class CustomMockException(
    val code: String,
    override val message: String
) : RuntimeException()
