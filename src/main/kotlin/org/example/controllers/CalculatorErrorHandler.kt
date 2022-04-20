package org.example.controllers

import org.example.objects.error.DivideByZeroException
import org.example.objects.response.CalculationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CalculatorErrorHandler {
    @ExceptionHandler(DivideByZeroException::class)
    fun handleDivideByZero(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError("Attempted division by zero"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ArithmeticException::class)
    fun handleArithmeticException(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError(e.message ?: "A computation error occurred"), HttpStatus.BAD_REQUEST)
}