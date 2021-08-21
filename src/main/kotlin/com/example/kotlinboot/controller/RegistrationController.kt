package com.example.kotlinboot.controller

import com.example.kotlinboot.model.User
import com.example.kotlinboot.repository.UserRepository
import mu.KLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistrationController(private val userRepository: UserRepository,
                             private val bCryptPasswordEncoder: PasswordEncoder) {
    companion object : KLogging()

    @GetMapping("/register")
    fun registerForm(): String {
        return "registration"
    }

    @PostMapping("/register")
    fun registration(form: RegistrationForm): String {
        logger.info("User register [${form}]")
        userRepository.save(form.convertWith(bCryptPasswordEncoder))
        return "redirect:/login"
    }
}

data class RegistrationForm(val username: String,
                            val password: String,
                            val fullname: String,
                            val street: String,
                            val city: String,
                            val state: String,
                            val zip: String,
                            val phone: String) {
    fun convertWith(passwordEncoder: PasswordEncoder): User = User(username = username, password = passwordEncoder.encode(password), fullname = fullname, state = street, city = city, street = state, zip = zip, phoneNumber = phone)
}
