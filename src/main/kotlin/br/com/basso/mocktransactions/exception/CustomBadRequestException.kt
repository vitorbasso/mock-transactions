package br.com.basso.mocktransactions.exception

import br.com.basso.mocktransactions.enum.ErrorCode

class CustomBadRequestException (error: ErrorCode) : CustomMockException(error.name, error.cause)
