package org.example.services

import org.example.objects.response.CalculationResponse

interface CalculatorService {
    fun add(vararg addends: Double): CalculationResponse

    fun subtract(minuend: Double, subtrahend: Double): CalculationResponse

    fun multiply(vararg factors: Double): CalculationResponse

    fun divide(dividend: Double, divisor: Double): CalculationResponse
}