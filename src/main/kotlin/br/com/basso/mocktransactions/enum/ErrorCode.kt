package br.com.basso.mocktransactions.enum

enum class ErrorCode(val cause: String) {
    CODE_00("error.request.path.client-id"),
    CODE_01("error.request.path.transaction-year"),
    CODE_02("error.request.path.transaction-month")
}
