package com.example.kotlinboot.controller

import com.example.kotlinboot.integration.StringGateway
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDateTime

@Controller
class IntegrationController(private val stringGateway: StringGateway) {

    @GetMapping("/integration/{value}")
    @ResponseBody
    fun getValue(@PathVariable("value") value: String): Unit {
        val current = LocalDateTime.now()

        stringGateway.send(current.toString(), value)
    }
}
