package com.example.kotlinboot.repository

import com.example.kotlinboot.model.ComplexFoo
import com.example.kotlinboot.model.Foo
import com.example.kotlinboot.model.IntFoo
import com.example.kotlinboot.model.StringFoo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FooRepository: JpaRepository<Foo, Long> {
}

@Repository
interface StringFooRepository: CrudRepository<StringFoo, Long> {
}

@Repository
interface IntFooRepository: JpaRepository<IntFoo, Long> {
}

@Repository
interface ComplexFooRepository: CrudRepository<ComplexFoo, Long> {
}
