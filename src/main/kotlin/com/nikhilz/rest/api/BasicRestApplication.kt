package com.nikhilz.rest.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BasicRestApplication

fun main(args: Array<String>) {
    runApplication<BasicRestApplication>(*args)
}
