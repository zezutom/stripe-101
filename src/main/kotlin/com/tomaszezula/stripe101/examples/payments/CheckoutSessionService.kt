package com.tomaszezula.stripe101.examples.payments

import com.stripe.model.checkout.Session
import com.tomaszezula.stripe101.config.CheckoutSessionConfig
import org.springframework.stereotype.Service

@Service
class CheckoutSessionService(private val config: CheckoutSessionConfig) {

    fun createCheckoutSession(checkoutSessionBuilder: CheckoutSessionBuilder): Session {
        return checkoutSessionBuilder.build(config.successUrl, config.cancelUrl)
    }

}