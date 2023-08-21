package com.tomaszezula.stripe101.model;

public record Price(String id,
                    String productId,
                    Long amount,
                    Currency currency,
                    boolean isRecurring
) {

  public static Price fromApi(com.stripe.model.Price price) {
    return new Price(price.getId(), price.getProduct(), price.getUnitAmount(),
        new Currency(price.getCurrency()), price.getRecurring() != null);
  }
}
