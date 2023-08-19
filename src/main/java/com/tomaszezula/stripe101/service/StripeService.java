package com.tomaszezula.stripe101.service;

import com.tomaszezula.stripe101.client.IStripeClient;
import com.tomaszezula.stripe101.model.Price;
import com.tomaszezula.stripe101.model.Product;
import com.tomaszezula.stripe101.model.SubscriptionPeriod;
import com.tomaszezula.stripe101.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StripeService implements IStripeService {

    private final IStripeClient stripeClient;

    public StripeService(@Autowired IStripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public Result<Product> createProduct(String name, String description) {
        try {
            var product = stripeClient.createProduct(name, description);
            if (product != null) {
                return Result.some(Product.fromApi(product));
            } else {
                return Result.error("Failed to create product");
            }
        } catch (Throwable t) {
            return Result.error("Failed to create product");
        }
    }

    @Override
    public Result<Product> getProduct(String id) {
        try {
            var product = stripeClient.getProduct(id);
            if (product != null) {
                return Result.some(Product.fromApi(product));
            } else {
                return Result.none();
            }
        } catch (Throwable t) {
            return Result.error("Failed to get product");
        }
    }

    @Override
    public Result<Boolean> deleteProduct(String id) {
        try {
            var deleted = stripeClient.deleteProduct(id);
            return Result.some(Objects.requireNonNullElse(deleted, false));
        } catch (Throwable t) {
            return Result.error("Failed to delete product");
        }
    }

    @Override
    public Result<Price> addUnitPrice(String id, String currency, Long amount) {
        try {
            var price = stripeClient.addUnitPrice(id, currency, amount);
            if (price != null) {
                return Result.some(Price.fromApi(price));
            } else {
                return Result.error("Failed to add unit price");
            }
        } catch (Throwable t) {
            return Result.error("Failed to add unit price");
        }
    }

    @Override
    public Result<Price> addRecurringPrice(String id, String currency, Long amount, SubscriptionPeriod period) {
        try {
            var price = stripeClient.addRecurringPrice(id, currency, amount, period);
            if (price != null) {
                return Result.some(Price.fromApi(price));
            } else {
                return Result.error("Failed to add recurring price");
            }
        } catch (Throwable t) {
            return Result.error("Failed to add recurring price");
        }
    }
}
