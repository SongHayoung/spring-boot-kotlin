package com.example.kotlinboot.assembler

import com.example.kotlinboot.controller.ShopController
import com.example.kotlinboot.model.Shop
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.Link
import org.springframework.hateoas.LinkRelation
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.hateoas.server.mvc.withRel
import org.springframework.stereotype.Component
import java.util.*

@Component
class ShopRepresentationModelAssembler(controllerClass: Class<*> = ShopController::class.java,
                                       resourceType: Class<ShopRepresentationModel> = ShopRepresentationModel::class.java):
    RepresentationModelAssemblerSupport<Shop, ShopRepresentationModel>(controllerClass, resourceType) {
    override fun toModel(entity: Shop): ShopRepresentationModel {
        return ShopRepresentationModel(
            entity.productName,
            entity.price,
            linkTo<ShopController> { getShop(entity.id!!) } withRel  IanaLinkRelations.SELF,
            linkTo<ShopController> { deleteShop(entity.id!!) } withRel LinkRelation.of("delete")
        )
    }
}
