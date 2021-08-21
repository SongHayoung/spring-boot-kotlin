package com.example.kotlinboot.service

import com.example.kotlinboot.repository.UserRepository
import mu.KLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserRepositoryUserDetailService(private val userRepository: UserRepository): UserDetailsService {
    companion object : KLogging()

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("loadUserByUsername [$username]")
        val user = userRepository.findByUsername(username)

        return user?:throw UsernameNotFoundException("User [$username] not found!!!")
    }
}
