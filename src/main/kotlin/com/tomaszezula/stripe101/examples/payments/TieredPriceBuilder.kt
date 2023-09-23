package com.tomaszezula.stripe101.examples.payments

import com.stripe.Stripe
import com.stripe.model.Price

typealias PriceTier = Map<String, Any>

class TieredPriceBuilder {
    private var priceTiers: List<PriceTier> = mutableListOf()

    fun addPriceTier(quantity: Long? = null, unitAmount: Long): TieredPriceBuilder {
        val priceTier = mapOf(
            "up_to" to (quantity ?: "inf"),
            "unit_amount" to unitAmount
        )
        priceTiers += priceTier
        return this
    }

    fun build(currency: String, productId: String, tiersMode: String): Price {
        return Price.create(
            mapOf(
                "currency" to currency,
                "product" to productId,
                "tiers_mode" to tiersMode,
                "tiers" to priceTiers,
                "recurring" to mapOf(
                    "interval" to "month",
                    "interval_count" to 1
                ),
                "billing_scheme" to "tiered"
            )
        )
    }
}

fun main() {
    Stripe.apiKey = "your api key"

    val builder = TieredPriceBuilder()
    val price = builder
        .addPriceTier(quantity = 5, unitAmount = 1000)
        .addPriceTier(unitAmount = 750)
        .build(
            currency = "usd",
            productId = "prod_OgmmKvjqDCDvcM",
            tiersMode = "graduated"
        )
    println(price)
}