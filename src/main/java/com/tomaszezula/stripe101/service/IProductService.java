package com.tomaszezula.stripe101.service;

import com.tomaszezula.stripe101.model.Product;
import com.tomaszezula.stripe101.util.Result;

/**
 * Creates and manages products in Stripe.
 */
public interface IProductService {

    /**
     * Creates a new product in Stripe.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @return the internal representation of a product
     */
    Result<Product> createProduct(String name, String description);

    /**
     * Retrieves a product from Stripe.
     *
     * @param id the product id
     * @return the product
     */
    Result<Product> getProduct(String id);

    /**
     * Deletes a product from Stripe.
     *
     * @param id the product id
     * @return true if the product was deleted, false otherwise
     */
    Result<Boolean> deleteProduct(String id);
}
