package com.example.kotlinboot.controller

import com.example.kotlinboot.model.User
import mu.KLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class HomeController {
    companion object : KLogging()

    @GetMapping("/")
    fun main(): String {
        return "main"
    }

    @GetMapping("/home")
    fun home(principal: Principal, authentication: Authentication, user: User): String {
        logger.info("Request received from principal [${principal.name}]")
        logger.info("Request received from authentication [${authentication.principal}]")
        logger.info("Request received from user [$user]")

        return "home"
    }
}
