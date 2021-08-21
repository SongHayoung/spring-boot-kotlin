package com.example.kotlinboot.controller

import com.example.kotlinboot.model.Shop
import com.example.kotlinboot.repository.ShopRepository
import mu.KLogging
import org.springframework.hateoas.*
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.hateoas.server.mvc.withRel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ShopController(private val shopRepository: ShopRepository) {
    companion object : KLogging()

    /**
     * https://spring.io/blog/2019/03/05/spring-hateoas-1-0-m1-released#overhaul
     * 변경사항 참고
     * Resource -> EntityModel
     */
    @GetMapping("/shops")
    fun getShops(): CollectionModel<EntityModel<Shop>> {
        val shops = shopRepository.findAll().map {
            EntityModel.of(it,
                linkTo<ShopController> { getShop(it.id!!) } withRel  IanaLinkRelations.SELF,
                linkTo<ShopController> { deleteShop(it.id!!) } withRel LinkRelation.of("delete")
            )}

        return CollectionModel.of(shops, WebMvcLinkBuilder.linkTo(ShopController::class.java).slash("shop").slash("save").withRel("post"))
    }

    @GetMapping("/shop/{id}")
    fun getShop(@PathVariable id: Long): EntityModel<Shop> {
        return EntityModel.of(shopRepository.findById(id).orElseThrow{IllegalArgumentException("There is no item with ${id}")},
            linkTo<ShopController> { deleteShop(id) } withRel LinkRelation.of("delete")
        )
    }

    @PostMapping("/shop/save")
    fun saveShop(@ModelAttribute shop: Shop): ResponseEntity<Unit> {
        shopRepository.save(shop)

        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/shop/{id}")
    fun deleteShop(@PathVariable id: Long): ResponseEntity<Unit> {
        shopRepository.deleteById(id)

        return ResponseEntity(HttpStatus.OK)
    }
}
