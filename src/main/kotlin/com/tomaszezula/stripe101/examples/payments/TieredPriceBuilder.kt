package com.tomaszezula.stripe101.examples.payments

import com.stripe.model.Price

typealias PriceTier = Map<String, Any>

class TieredPriceBuilder {
    private var priceTiers: List<PriceTier> = mutableListOf()

    fun addPriceTier(quantity: Long, unitAmount: Long): TieredPriceBuilder {
        val priceTier = mapOf(
            "up_to" to quantity,
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
                "tiers" to priceTiers
            )
        )
    }
}