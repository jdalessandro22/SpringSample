package org.example.controllers

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.response.CalculationResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CalculatorErrorHandler {
    private companion object {
        private val LOGGER = LoggerFactory.getLogger(CalculatorErrorHandler::class.java)
    }

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
    fun handleArithmeticException(e: Exception): ResponseEntity<CalculationResponse> {
        LOGGER.error("Computation error", e)
        return ResponseEntity(
            CalculationResponse.asError(e.message ?: "A computation error occurred"),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleWrongMessageFormat(e: Exception): ResponseEntity<CalculationResponse> {
        LOGGER.error("Received improperly formatted request", e)
        return ResponseEntity(
            CalculationResponse.asError(e.message ?: "Improperly formatted request"),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleWrongHttpVerb(e: HttpRequestMethodNotSupportedException): ResponseEntity<CalculationResponse> {
        LOGGER.error("Received request with unsupported request method {}", e.method)
        return ResponseEntity(
            CalculationResponse.asError("Wrong HTTP verb ${e.method}: please use one of ${e.supportedMethods.contentToString()}"),
            HttpStatus.METHOD_NOT_ALLOWED,
        )
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleWrongRequestContentType(e: HttpMediaTypeNotSupportedException): ResponseEntity<CalculationResponse> {
        LOGGER.error("Received request body with unsupported content type {}", e.contentType)
        return ResponseEntity(
            CalculationResponse.asError("Wrong content type ${e.contentType} for request body: please use application/json"),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        )
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    fun handleUnfulfillableAcceptHeader(e: HttpMediaTypeNotAcceptableException): ResponseEntity<String> {
        LOGGER.error("Received request with unfulfillable Accept header")
        return ResponseEntity(
            "Unable to respond with content type requested in Accept header. This endpoint produces application/json.",
            HttpStatus.NOT_ACCEPTABLE,
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherException(e: Exception): ResponseEntity<CalculationResponse> {
        LOGGER.error("Unexpected error", e)
        return ResponseEntity(
            CalculationResponse.asError(e.message ?: "An unknown error occurred"),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}