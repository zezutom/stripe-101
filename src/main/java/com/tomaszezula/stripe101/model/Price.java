package com.tomaszezula.stripe101.model;

public record Price(String currency, Long amount, String id) {

    public static Price fromApi(com.stripe.model.Price price) {
        return new Price(price.getCurrency(), price.getUnitAmount(), price.getId());
    }
}
