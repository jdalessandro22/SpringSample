package org.example.services

import org.example.objects.DivideByZeroException
import org.example.objects.NotEnoughAddendsException
import org.example.objects.NotEnoughFactorsException
import org.example.objects.response.CalculationResponse
import spock.lang.Specification

import java.math.RoundingMode

class CalculatorServiceSpec extends Specification {
    private CalculatorService calculatorService = new CalculatorServiceImpl()

    def "add :: happy path"() {
        given:
        double[] addends = new double[]{4.7, 5.8, 2}

        when:
        CalculationResponse response = calculatorService.add(addends)

        then:
        response.isSuccess()
        response.result == 12.5
    }

    def "add :: not enough arguments"() {
        when:
        calculatorService.add(2.4)

        then:
        thrown(NotEnoughAddendsException)
    }

    def "subtract"() {
        when:
        CalculationResponse response = calculatorService.subtract(7.2, 4.3)

        then:
        response.isSuccess()
        roundToPlaces(response.result, 1) == 2.9
    }

    def "multiply :: happy path"() {
        given:
        double[] factors = new double[]{4.7, 5.8, 2}

        when:
        CalculationResponse response = calculatorService.multiply(factors)

        then:
        response.isSuccess()
        response.result == 54.52
    }

    def "multiply :: not enough arguments"() {
        when:
        calculatorService.multiply(2.4)

        then:
        thrown(NotEnoughFactorsException)
    }

    def "divide :: happy path"() {
        when:
        CalculationResponse response = calculatorService.divide(52.8, 6)

        then:
        response.isSuccess()
        roundToPlaces(response.result, 1) == 8.8
    }

    def "divide :: attempted division by zero"() {
        when:
        calculatorService.divide(23.1, 0)

        then:
        thrown(DivideByZeroException)
    }

    private BigDecimal roundToPlaces(double toRound, int places) {
        return new BigDecimal(toRound).setScale(places, RoundingMode.HALF_EVEN)
    }
}
