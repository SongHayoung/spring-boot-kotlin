package com.example.kotlinboot.mapstruct

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PersonMapper {
    @Mapping(source = "phoneNumber", target = "phone")
    fun convertToDto(person: Person) : PersonDto

    @InheritInverseConfiguration
    fun convertToModel(personDto: PersonDto) : Person
}
