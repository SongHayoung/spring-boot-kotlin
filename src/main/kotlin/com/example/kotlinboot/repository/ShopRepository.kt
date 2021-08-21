package com.example.kotlinboot.repository

import com.example.kotlinboot.model.Shop
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopRepository: CrudRepository<Shop, Long> {
}
