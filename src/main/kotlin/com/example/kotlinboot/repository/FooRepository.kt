package com.example.kotlinboot.repository

import com.example.kotlinboot.model.ComplexFoo
import com.example.kotlinboot.model.Foo
import com.example.kotlinboot.model.IntFoo
import com.example.kotlinboot.model.StringFoo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FooRepository: CrudRepository<Foo, Long> {
}

@Repository
interface StringFooRepository: CrudRepository<StringFoo, Long> {
}

@Repository
interface IntFooRepository: CrudRepository<IntFoo, Long> {
}

@Repository
interface ComplexFooRepository: CrudRepository<ComplexFoo, Long> {
}
