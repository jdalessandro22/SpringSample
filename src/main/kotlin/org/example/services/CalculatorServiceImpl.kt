package org.example.services

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.response.CalculationResponse
import org.example.utils.product
import org.example.utils.sum

class CalculatorServiceImpl: CalculatorService {
    override fun add(vararg addends: Double): CalculationResponse
        = if (addends.size < 2)
            throw NotEnoughAddendsException()
        else CalculationResponse.asSuccess(addends.sum())

    override fun subtract(minuend: Double, subtrahend: Double): CalculationResponse
        = CalculationResponse.asSuccess(minuend - subtrahend)

    override fun multiply(vararg factors: Double): CalculationResponse
        = if (factors.size < 2)
            throw NotEnoughFactorsException()
        else CalculationResponse.asSuccess(factors.product())

    override fun divide(dividend: Double, divisor: Double): CalculationResponse
        = if (divisor == 0.0)
            throw DivideByZeroException()
        else CalculationResponse.asSuccess(dividend / divisor)
}