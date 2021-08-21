package com.example.kotlinboot.assembler

import com.example.kotlinboot.annotation.AllOpen
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@AllOpen
class ShopRepresentationModel(
    val productName: String,
    val price: Long,
    vararg link: Link
): RepresentationModel<ShopRepresentationModel>(link.asList()) {
}
