package com.tomaszezula.stripe101.client;

import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
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
   * Adds a one-time price to a product. Suitable for one-time purchases.
   *
   * @param productId the product id
   * @param currency  the price currency
   * @param amount    the monetary amount
   * @return the created unit price
   */
  Price addOneTimePrice(String productId, String currency, Long amount);

  /**
   * Adds a recurring price to a product. Suitable for subscriptions.
   *
   * @param productId the product id
   * @param currency  the price currency
   * @param amount    the monetary amount
   * @return the created recurring price
   */
  Price addRecurringPrice(String productId, String currency, Long amount,
      SubscriptionPeriod period);

  /***
   * Get all prices for a product
   * @param productId the product id
   * @return the price collection
   */
  PriceCollection getAllPrices(String productId);
}
