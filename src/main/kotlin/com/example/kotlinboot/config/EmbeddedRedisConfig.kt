package com.example.kotlinboot.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

//@Configuration
class EmbeddedRedisConfig(redisProperties: RedisProperties) {
    private var redisServer: RedisServer = RedisServer(redisProperties.port)

    @PostConstruct
    fun start() = redisServer.start()

    @PreDestroy
    fun stop() = redisServer.stop()
}
