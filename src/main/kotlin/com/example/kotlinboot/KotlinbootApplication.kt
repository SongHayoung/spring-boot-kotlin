package com.example.kotlinboot

import com.example.kotlinboot.config.YamlProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableConfigurationProperties(value = arrayOf(YamlProperties::class))
class KotlinbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinbootApplication>(*args)
}
