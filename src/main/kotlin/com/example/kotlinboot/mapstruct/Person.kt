package com.example.kotlinboot.mapstruct

import java.time.LocalDate

data class Person(var firstName: String?, var lastName: String?, var phoneNumber: String?, var birthdate: LocalDate?)
data class PersonDto(var firstName: String?, var lastName: String?, var phone: String?, var birthdate: LocalDate?)

