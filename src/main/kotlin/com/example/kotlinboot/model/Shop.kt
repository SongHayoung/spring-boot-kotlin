package com.example.kotlinboot.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Shop(@Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           var id: Long? = null,
           val productName: String,
           val price: Long) {
}
