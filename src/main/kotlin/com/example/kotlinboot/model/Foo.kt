package com.example.kotlinboot.model

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
class Foo(@Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          var id: Long?,
          val name: String) {
    override fun toString(): String {
        return "Foo $id $name"
    }
}

@Entity
data class FooSub(override var id: Long? = null,
                  override val name: String,
                  @ManyToOne(fetch = FetchType.LAZY)
                  var parent: ComplexFoo? = null): Foo(id, name)

@Entity
class IntFoo(id: Long? = null, name: String, val value: Int): FooSub(id, name) {
    override fun toString(): String {
        return "IntFoo $id $name $value"
    }
}

@Entity
class StringFoo(id: Long? = null, name: String, val value: String): FooSub(id, name) {
    override fun toString(): String {
        return "StringFoo $id $name $value"
    }
}

@Entity
class ComplexFoo(id: Long? = null,name: String, @OneToMany(fetch = FetchType.LAZY) val values: List<FooSub>): Foo(id, name)
