package com.example.kotlinboot.integration

import mu.KLogging
import org.springframework.integration.router.AbstractMessageRouter
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import java.util.*

class IntegerRouter(private val evenChannel: MessageChannel, private val oddChannel: MessageChannel): AbstractMessageRouter() {
    companion object : KLogging()

    override fun determineTargetChannels(message: Message<*>?): MutableCollection<MessageChannel> {
        val payload = message?.payload as Int
        val headers = message.headers

        val date = Date(headers.timestamp!!)

        logger.info("Router get integer value : [$payload] at [$date]")

        return if (payload % 2 == 0) mutableListOf(evenChannel) else mutableListOf(oddChannel)
    }
}
