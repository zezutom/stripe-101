package com.tomaszezula.stripe101.model;

import java.util.Collection;

public record Product(String name, String description, String id, Collection<Price> prices) {

  public static Product fromApi(com.stripe.model.Product product, Collection<com.stripe.model.Price> prices) {
    return new Product(product.getName(), product.getDescription(), product.getId(),
        prices.stream().map(Price::fromApi).toList());
  }
}
