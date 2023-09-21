package com.tomaszezula.stripe101.client

import com.stripe.model.Price
import com.stripe.model.PriceCollection
import com.stripe.model.Product
import com.tomaszezula.stripe101.model.SubscriptionPeriod

interface IStripeClient {

    /**
     * Creates a new product in Stripe.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @return the internal representation of a product
     */
    fun createProduct(name: String, description: String): Product

    /**
     * Retrieves a product from Stripe.
     *
     * @param id the product id
     * @return the product
     */
    fun getProduct(id: String): Product

    /**
     * Deletes a product from Stripe.
     *
     * @param id the product id
     * @return true if the product was deleted, false otherwise
     */
    fun deleteProduct(id: String): Boolean

    /**
     * Adds a one-time price to a product. Suitable for one-time purchases.
     *
     * @param productId the product id
     * @param currency  the price currency
     * @param amount    the monetary amount
     * @return the created unit price
     */
    fun addOneTimePrice(productId: String, currency: String, amount: Long): Price

    /**
     * Adds a recurring price to a product. Suitable for subscriptions.
     *
     * @param productId the product id
     * @param currency  the price currency
     * @param amount    the monetary amount
     * @return the created recurring price
     */
    fun addRecurringPrice(
        productId: String,
        currency: String,
        amount: Long,
        period: SubscriptionPeriod
    ): Price

    /***
     * Get all prices for a product
     * @param productId the product id
     * @return the price collection
     */
    fun getAllPrices(productId: String): PriceCollection
}