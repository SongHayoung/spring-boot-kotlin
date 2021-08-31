package com.example.kotlinboot

import com.example.kotlinboot.config.YamlProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [RedisAutoConfiguration::class, RedisRepositoriesAutoConfiguration::class])
@EnableConfigurationProperties(value = arrayOf(YamlProperties::class))
@EnableJpaRepositories("com.example.kotlinboot.repository")
@EntityScan("com.example.kotlinboot")
class KotlinbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinbootApplication>(*args)
}
