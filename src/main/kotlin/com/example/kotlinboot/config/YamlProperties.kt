package com.example.kotlinboot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "yaml")
data class YamlProperties (val profile: String, val values: List<String>)
