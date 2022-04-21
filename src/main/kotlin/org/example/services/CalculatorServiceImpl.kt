package org.example.services

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.response.CalculationResponse
import org.example.utils.product
import org.example.utils.sum
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CalculatorServiceImpl: CalculatorService {
    private companion object {
        private val LOGGER = LoggerFactory.getLogger(CalculatorServiceImpl::class.java)
    }

    override fun add(vararg addends: Double): CalculationResponse
        = if (addends.size < 2) {
            LOGGER.error("Received addition request with too few arguments ({})", addends.size)
            throw NotEnoughAddendsException()
        }
        else {
            LOGGER.info("Computing sum of {}", addends.contentToString())
            CalculationResponse.asSuccess(addends.sum())
        }

    override fun subtract(minuend: Double, subtrahend: Double): CalculationResponse {
        LOGGER.info("Computing {} - {}", minuend, subtrahend)
        return CalculationResponse.asSuccess(minuend - subtrahend)
    }

    override fun multiply(vararg factors: Double): CalculationResponse
        = if (factors.size < 2) {
            LOGGER.error("Received multiplication request with too few arguments ({})", factors.size)
            throw NotEnoughFactorsException()
        }
        else {
            LOGGER.info("Computing product of {}", factors.contentToString())
            CalculationResponse.asSuccess(factors.product())
        }

    override fun divide(dividend: Double, divisor: Double): CalculationResponse
        = if (divisor == 0.0) {
            LOGGER.error("Received request for divison by zero")
            throw DivideByZeroException()
        }
        else {
            LOGGER.info("Computing {} / {}", dividend, divisor)
            CalculationResponse.asSuccess(dividend / divisor)
        }
}