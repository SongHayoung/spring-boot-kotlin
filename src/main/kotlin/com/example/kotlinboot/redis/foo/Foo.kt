package com.example.kotlinboot.redis.foo

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDateTime

@RedisHash
open class Foo(@Id var Id: Long? = null,
               @Indexed val name: String,
               @TimeToLive var ttl: Long

) {
}

@RedisHash
class IntFoo(id: Long? = null, name: String, val value: Int, ttl: Long): Foo(id, name, ttl)

@RedisHash
class StringFoo(id: Long? = null, name: String, val value: String, ttl: Long): Foo(id, name, ttl)

@RedisHash
class DateFoo(id: Long? = null, name: String, @Indexed val start: LocalDateTime, @Indexed val end: LocalDateTime, ttl: Long): Foo(id, name, ttl)
