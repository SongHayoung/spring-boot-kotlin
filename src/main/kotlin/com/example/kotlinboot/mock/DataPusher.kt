package com.example.kotlinboot.mock

import com.example.kotlinboot.model.Shop
import com.example.kotlinboot.repository.ShopRepository
import mu.KLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataPusher(private val shopRepository: ShopRepository): CommandLineRunner {
    companion object : KLogging()

    override fun run(vararg args: String?) {
        val shops = listOf(
            Shop(productName = "item1", price = 1000),
            Shop(productName = "item2", price = 2000),
            Shop(productName = "item3", price = 3000),
            Shop(productName = "item4", price = 4000),
            Shop(productName = "item5", price = 5000))

        shopRepository.saveAll(shops)
        logger.info("Data pusher pushed Shop")
    }
}
