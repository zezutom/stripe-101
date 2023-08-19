package com.tomaszezula.stripe101.model;

public record Product(String name, String description, String id) {

    public static Product fromApi(com.stripe.model.Product product) {
        return new Product(product.getName(), product.getDescription(), product.getId());
    }
}
