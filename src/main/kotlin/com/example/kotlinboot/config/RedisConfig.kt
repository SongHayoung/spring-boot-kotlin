package com.example.kotlinboot.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisKeyValueAdapter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer


@Configuration
@EnableRedisRepositories("com.example.kotlinboot.redis", enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
class RedisConfig {
    @Bean
    @ConfigurationProperties("spring.redis")
    fun redisProperties(): RedisProperties {
        return RedisProperties()
    }

    @Bean
    fun redisConnectionFactory(redisProperties: RedisProperties): LettuceConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.keySerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()

        return redisTemplate
    }
}
