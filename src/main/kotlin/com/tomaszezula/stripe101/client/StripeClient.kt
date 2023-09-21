package com.tomaszezula.stripe101.client

import com.stripe.model.Price
import com.stripe.model.PriceCollection
import com.stripe.model.Product
import com.stripe.param.PriceCreateParams
import com.stripe.param.PriceListParams
import com.stripe.param.ProductCreateParams
import com.tomaszezula.stripe101.model.SubscriptionPeriod
import com.tomaszezula.stripe101.toInterval
import com.tomaszezula.stripe101.tryRun

class StripeClient : IStripeClient {
    override fun createProduct(name: String, description: String): Product {
        return tryRun {
            val productParams = ProductCreateParams.builder()
                .setName(name)
                .setDescription(description)
                .build()
            Product.create(productParams)
        }
    }

    override fun getProduct(id: String): Product {
        return tryRun {
            Product.retrieve(id)
        }
    }

    override fun deleteProduct(id: String): Boolean {
        return tryRun {
            Product.retrieve(id).delete() != null
        }
    }

    override fun addOneTimePrice(productId: String, currency: String, amount: Long): Price {
        return tryRun {
            val priceParams = PriceCreateParams.builder()
                .setCurrency(currency)
                .setUnitAmount(amount)
                .setProduct(productId)
                .build()
            Price.create(priceParams)
        }
    }

    override fun addRecurringPrice(
        productId: String,
        currency: String,
        amount: Long,
        period: SubscriptionPeriod
    ): Price {
        return tryRun {
            val priceParams = PriceCreateParams.builder()
                .setCurrency(currency)
                .setUnitAmount(amount)
                .setProduct(productId)
                .setRecurring(
                    PriceCreateParams.Recurring.builder()
                        .setInterval(period.toInterval())
                        .build()
                )
                .build()
            Price.create(priceParams)
        }
    }

    override fun getAllPrices(productId: String): PriceCollection {
        return tryRun {
            val params = PriceListParams
                .builder()
                .setProduct(productId)
                .setActive(true)
                .build()
            Price.list(params)
        }
    }
}