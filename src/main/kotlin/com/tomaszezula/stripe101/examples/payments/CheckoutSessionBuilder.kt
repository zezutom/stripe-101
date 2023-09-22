package com.tomaszezula.stripe101.examples.payments

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
                "mode" to "payment",
                "success_url" to successUrl,
                "cancel_url" to cancelUrl
            )
        )
    }
}