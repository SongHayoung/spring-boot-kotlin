package com.example.kotlinboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class KotlinbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinbootApplication>(*args)
}
