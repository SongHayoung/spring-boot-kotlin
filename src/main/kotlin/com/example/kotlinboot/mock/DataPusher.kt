package com.example.kotlinboot.mock

import com.example.kotlinboot.mapstruct.Person
import com.example.kotlinboot.mapstruct.PersonMapper
import com.example.kotlinboot.model.ComplexFoo
import com.example.kotlinboot.model.IntFoo
import com.example.kotlinboot.model.Shop
import com.example.kotlinboot.model.StringFoo
import com.example.kotlinboot.redis.foo.*
import com.example.kotlinboot.repository.ComplexFooRepository
import com.example.kotlinboot.repository.IntFooRepository
import com.example.kotlinboot.repository.ShopRepository
import com.example.kotlinboot.repository.StringFooRepository
import mu.KLogging
import org.mapstruct.factory.Mappers
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import javax.transaction.Transactional

@Component
class DataPusher(private val shopRepository: ShopRepository,
                 private val intFooRepository: IntFooRepository,
                 private val stringFooRepository: StringFooRepository,
                 private val complexFooRepository: ComplexFooRepository,
                 private val fooRedisRepository: FooRedisRepository,
                 private val intFooRedisRepository: IntFooRedisRepository,
                 private val stringFooRedisRepository: StringFooRedisRepository,
                 private val dateFooRedisRepository: DateFooRedisRepository,
                 private val personMapper: PersonMapper
): CommandLineRunner {
    companion object : KLogging()

    @Transactional
    override fun run(vararg args: String?) {
        //rdbRun()
        //redisRun()
        mapStructRun()
    }

    fun mapStructRun() {
        val person = Person("Samuel", "Jackson", "0123 334466", LocalDate.of(1948, 12, 21))

        val personDto = personMapper.convertToDto(person)
        println(personDto)

        val personModel = personMapper.convertToModel(personDto)
        println(personModel)
    }

    fun redisRun() {
        val foo = Foo(name = "foo", ttl = 60)
        logger.info("Redis foo $foo")

        val saveFoo = fooRedisRepository.save(foo)
        logger.info("Redis save foo $saveFoo")

        val findFoo = fooRedisRepository.findById(saveFoo.Id!!)
        logger.info("Redis find foo $findFoo")

        val nameFoo = fooRedisRepository.findByName(foo.name)
        logger.info("Redis name foo $nameFoo")

        val foo2 = Foo(name = "foo2", ttl = 6000)
        fooRedisRepository.save(foo2)

        val intFoo = IntFoo(name = "intFoo", value = 1, ttl = 6000)
        intFooRedisRepository.save(intFoo)

        val stringFoo = StringFoo(name = "stringFoo", value = "string", ttl = 6000)
        stringFooRedisRepository.save(stringFoo)

        val dateFoo = DateFoo(name = "dateFoo", start = LocalDateTime.now().minusDays(10), end = LocalDateTime.now().plusDays(10), ttl = 6000)
        dateFooRedisRepository.save(dateFoo)

        //val findDateFoo = dateFooRedisRepository.findAllByEndLessThanEqualAndStartGreaterThanEqual(LocalDateTime.now(), LocalDateTime.now())
        //logger.info("Redis date foo $findDateFoo")
    }

    fun rdbRun() {
        val shops = listOf(
            Shop(productName = "item1", price = 1000),
            Shop(productName = "item2", price = 2000),
            Shop(productName = "item3", price = 3000),
            Shop(productName = "item4", price = 4000),
            Shop(productName = "item5", price = 5000))

        shopRepository.saveAll(shops)
        logger.info("Data pusher pushed Shop")

        val intFoo = IntFoo(name = "int", value = 1)
        intFooRepository.save(intFoo)

        val stringFoo = StringFoo(name = "string", value = "String")
        stringFooRepository.save(stringFoo)

        val complexFoo = ComplexFoo(name = "complex", values = listOf(intFoo, stringFoo))
        complexFooRepository.save(complexFoo)

        val intFoo2 = IntFoo(name = "int", value = 2)
        intFooRepository.save(intFoo2)

        val stringFoo2 = StringFoo(name = "string", value = "String2")
        stringFooRepository.save(stringFoo2)

        val complexFoo2 = ComplexFoo(name = "complex2", values = listOf(intFoo2, stringFoo2))
        complexFooRepository.save(complexFoo2)

        val findComplexFoo = complexFooRepository.findById(complexFoo.id!!).get()
        val findComplexFoo2 = complexFooRepository.findById(complexFoo2.id!!).get()

        println(complexFoo.values)
        println(findComplexFoo.values)
        println(findComplexFoo2.values)
    }
}


