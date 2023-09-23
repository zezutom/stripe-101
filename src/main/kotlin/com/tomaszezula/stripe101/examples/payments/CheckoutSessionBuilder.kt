package com.tomaszezula.stripe101.examples.payments

import com.stripe.Stripe
import com.stripe.model.checkout.Session

typealias LineItem = Map<String, Any>

class CheckoutSessionBuilder {
    private var lineItems: List<LineItem> = mutableListOf()

    fun addLineItem(priceId: String, quantity: Long): CheckoutSessionBuilder {
        val lineItem = mapOf(
            "price" to priceId,
            "quantity" to quantity
        )
        lineItems += lineItem
        return this
    }

    fun build(successUrl: String, cancelUrl: String): Session {
        return Session.create(
            mapOf(
                "payment_method_types" to listOf("card"),
                "line_items" to lineItems,
                "mode" to "subscription",
                "success_url" to successUrl,
                "cancel_url" to cancelUrl
            )
        )
    }
}

fun main() {
    Stripe.apiKey = "your api key"

    val builder = CheckoutSessionBuilder()
    val session = builder
        .addLineItem(priceId = "price_1NtOfQGyWpmEflR8il6mXoFE", quantity = 1)
        .addLineItem(priceId = "price_1NtOj5GyWpmEflR8UyQMt48n", quantity = 5)
        .build(
            successUrl = "https://example.com/success",
            cancelUrl = "https://example.com/cancel"
        )
    println(session.url)
}