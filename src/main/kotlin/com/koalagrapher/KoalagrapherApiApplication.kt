package com.koalagrapher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KoalagrapherApiApplication

fun main(args: Array<String>) {
    runApplication<KoalagrapherApiApplication>(*args)
}
