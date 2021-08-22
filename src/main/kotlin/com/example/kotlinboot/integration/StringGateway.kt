package com.example.kotlinboot.integration

import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
@MessagingGateway(defaultRequestChannel = "valueChannel")
interface StringGateway {
    fun send(@Header("receivedAt") receivedAt: String, positiveNumber: String)
}
