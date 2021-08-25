package com.example.kotlinboot.mock

import com.example.kotlinboot.model.ComplexFoo
import com.example.kotlinboot.model.IntFoo
import com.example.kotlinboot.model.Shop
import com.example.kotlinboot.model.StringFoo
import com.example.kotlinboot.repository.ComplexFooRepository
import com.example.kotlinboot.repository.IntFooRepository
import com.example.kotlinboot.repository.ShopRepository
import com.example.kotlinboot.repository.StringFooRepository
import mu.KLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class DataPusher(private val shopRepository: ShopRepository,
                 private val intFooRepository: IntFooRepository,
                 private val stringFooRepository: StringFooRepository,
                 private val complexFooRepository: ComplexFooRepository
): CommandLineRunner {
    companion object : KLogging()

    @Transactional
    override fun run(vararg args: String?) {
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


