package com.example.kotlinboot.repository

import com.example.kotlinboot.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByUsername(username: String?): User?
}
