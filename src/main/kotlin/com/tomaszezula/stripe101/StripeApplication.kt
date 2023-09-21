package com.tomaszezula.stripe101

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StripeApplication

fun main(args: Array<String>) {
    runApplication<StripeApplication>(*args)
}
