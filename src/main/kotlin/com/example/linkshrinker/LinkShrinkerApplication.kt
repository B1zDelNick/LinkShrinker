package com.example.linkshrinker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class LinkShrinkerApplication

fun main(args: Array<String>)
{
    SpringApplication.run(LinkShrinkerApplication::class.java, *args)
}
