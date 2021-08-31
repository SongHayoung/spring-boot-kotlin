package com.example.kotlinboot.redis.foo

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class FooService(private val fooRedisRepository: FooRedisRepository,
                 private val redisTemplate: RedisTemplate<String, Any>
) {
    fun getKeys(): MutableSet<String> {
        return redisTemplate.keys("*")
    }

    fun saveFoo(foo: Foo): Foo {
        return fooRedisRepository.save(foo)
    }

    fun getFoo(id: Long): Foo {
        return fooRedisRepository.findById(id).get()
    }

    fun getFoo(name: String): Foo {
        return fooRedisRepository.findByName(name).get()
    }
}
