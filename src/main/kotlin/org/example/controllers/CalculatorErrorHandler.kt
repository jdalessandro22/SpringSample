package org.example.controllers

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.response.CalculationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CalculatorErrorHandler {
    @ExceptionHandler(DivideByZeroException::class)
    fun handleDivideByZero(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError("Attempted division by zero"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NotEnoughAddendsException::class)
    fun handleNotEnoughAddends(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError("Please supply at least two addends"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NotEnoughFactorsException::class)
    fun handleNotEnoughFactors(e: Exception): ResponseEntity<CalculationResponse>
            = ResponseEntity(CalculationResponse.asError("Please supply at least two factors"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ArithmeticException::class)
    fun handleArithmeticException(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError(e.message ?: "A computation error occurred"), HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleWrongMessageFormat(e: Exception): ResponseEntity<CalculationResponse>
        = ResponseEntity(CalculationResponse.asError(e.message ?: "Improperly formatted request"), HttpStatus.BAD_REQUEST)
}