package org.example.controllers

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.request.AddRequest
import org.example.objects.request.DivideRequest
import org.example.objects.request.MultiplyRequest
import org.example.objects.request.SubtractRequest
import org.example.objects.response.CalculationResponse
import org.example.services.CalculatorService
import org.example.utils.product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
class CalculatorController @Autowired constructor(
    private val calculatorService: CalculatorService
) {
    @PostMapping("add")
    fun add(@RequestBody request: AddRequest)
        = calculatorService.add(*request.addends)

    @PostMapping("subtract")
    fun subtract(@RequestBody request: SubtractRequest)
        = calculatorService.subtract(request.minuend, request.subtrahend)

    @PostMapping("multiply")
    fun multiply(@RequestBody request: MultiplyRequest)
        = calculatorService.multiply(*request.factors)

    @PostMapping("divide")
    fun divide(@RequestBody request: DivideRequest)
        = calculatorService.divide(request.dividend, request.divisor)
}