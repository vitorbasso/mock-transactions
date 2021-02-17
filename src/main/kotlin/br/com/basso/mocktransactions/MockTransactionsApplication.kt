package br.com.basso.mocktransactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MockTransactionsApplication

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
	runApplication<MockTransactionsApplication>(*args)
}
