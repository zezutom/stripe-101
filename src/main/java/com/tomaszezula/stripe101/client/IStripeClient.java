package com.tomaszezula.stripe101.client;

import com.stripe.model.Price;
import com.stripe.model.Product;
import com.tomaszezula.stripe101.model.SubscriptionPeriod;

public interface IStripeClient {
    /**
     * Creates a new product in Stripe.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @return the internal representation of a product
     */
    Product createProduct(String name, String description);

    /**
     * Retrieves a product from Stripe.
     *
     * @param id the product id
     * @return the product
     */
    Product getProduct(String id);

    /**
     * Deletes a product from Stripe.
     *
     * @param id the product id
     * @return true if the product was deleted, false otherwise
     */
    Boolean deleteProduct(String id);

    /**
     * Adds a unit price to a product.
     *
     * @param id       the product id
     * @param currency the price currency
     * @param amount   the monetary amount
     * @return the created unit price
     */
    Price addUnitPrice(String id, String currency, Long amount);

    /**
     * Adds a recurring price to a product.
     *
     * @param id       the product id
     * @param currency the price currency
     * @param amount   the monetary amount
     * @return the created recurring price
     */
    Price addRecurringPrice(String id, String currency, Long amount, SubscriptionPeriod period);

}
