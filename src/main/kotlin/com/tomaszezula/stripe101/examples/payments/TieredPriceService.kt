package com.tomaszezula.stripe101.examples.payments

import com.stripe.model.Price
import org.springframework.stereotype.Service

@Service
class TieredPriceService {
    fun createTieredPrice(builder: TieredPriceBuilder, productId: String, tiersMode: String): Price {
        return builder.build(currency = "usd", productId = productId, tiersMode = tiersMode)
    }
}