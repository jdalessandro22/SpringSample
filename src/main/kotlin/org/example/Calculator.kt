package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Calculator

fun main(args: Array<String>) {
    runApplication<Calculator>(*args)
}