package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Calculator

fun main(args: Array<String>) {
    runApplication<Calculator>(*args)
}