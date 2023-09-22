package com.tomaszezula.stripe101.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "stripe.checkout.session")
data class CheckoutSessionConfig(val successUrl: String, val cancelUrl: String)
