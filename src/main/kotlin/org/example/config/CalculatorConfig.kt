package org.example.config

import org.example.services.CalculatorServiceImpl
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Suppress("unused")
@Configuration
class CalculatorConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun calculatorService() = CalculatorServiceImpl()
}