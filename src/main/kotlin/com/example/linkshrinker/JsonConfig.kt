package com.example.linkshrinker

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class JsonConfig
{
    @Bean open fun kotlinModule() = KotlinModule()
}