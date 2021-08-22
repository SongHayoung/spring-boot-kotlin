package com.example.kotlinboot.config

import com.example.kotlinboot.integration.IntegerRouter
import mu.KLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.*
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.router.AbstractMessageRouter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

@Configuration
class IntegrationConfig {
    companion object : KLogging()

    @Filter(inputChannel = "valueChannel", outputChannel = "filterChannel")
    fun filter(positiveNumber: String): Boolean {
        logger.info("Integration filter received value : [$positiveNumber]")

        return positiveNumber.toIntOrNull()?.let { true } ?: false
    }

    @Transformer(inputChannel = "filterChannel", outputChannel = "routerChannel")
    fun transform(positiveNumber: String): Int {
        logger.info("Integration Transform get integerValue as String [$positiveNumber]")

        return positiveNumber.toInt()
    }

    @Bean
    @Router(inputChannel = "routerChannel")
    fun integerRouter(): AbstractMessageRouter = IntegerRouter(evenChannel(), oddChannel())

    @Bean
    fun evenChannel(): MessageChannel = DirectChannel()

    @Bean
    fun oddChannel(): MessageChannel = DirectChannel()

    @Bean
    @ServiceActivator(inputChannel = "evenChannel")
    fun evenHandler(): MessageHandler {
        return MessageHandler { logger.info("Even handler get message : [$it]") }
    }

    @Bean
    @ServiceActivator(inputChannel = "oddChannel")
    fun oddHandler(): MessageHandler {
        return MessageHandler { logger.info("Odd handler get message : [$it]") }
    }
}
