package com.tomaszezula.stripe101.config

import com.stripe.Stripe
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(CheckoutSessionConfig::class)
class StripeConfig(@Value("\${stripe.secret-key}") private val secretKey: String) {
    init {
        Stripe.apiKey = secretKey
    }

}