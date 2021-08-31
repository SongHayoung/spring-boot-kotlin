package com.example.kotlinboot.redis.foo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface FooRedisRepository: CrudRepository<Foo, Long> {
    fun findByName(name: String): Optional<Foo>
}

@Repository
interface IntFooRedisRepository: CrudRepository<IntFoo, Long> {
}

@Repository
interface StringFooRedisRepository: CrudRepository<StringFoo, Long> {
}

@Repository
interface DateFooRedisRepository: CrudRepository<DateFoo, Long> {
    /**
     * 미지원 쿼리
     * https://docs.spring.io/spring-data/redis/docs/current/reference/html/#redis.repositories.queries
     */
    //fun findAllByEndLessThanEqualAndStartGreaterThanEqual(end: LocalDateTime, start: LocalDateTime)
}
