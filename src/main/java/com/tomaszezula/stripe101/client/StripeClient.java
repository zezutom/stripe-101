package com.tomaszezula.stripe101.client;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.tomaszezula.stripe101.model.SubscriptionPeriod;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeClient implements IStripeClient {
    @Override
    public Product createProduct(String name, String description) {
        try {
            var productParams = ProductCreateParams.builder()
                    .setName(name)
                    .setDescription(description)
                    .build();
            return Product.create(productParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct(String id) {
        try {
            return Product.retrieve(id);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteProduct(String id) {
        try {
            var maybeProduct = Optional.ofNullable(Product.retrieve(id));
            if (maybeProduct.isPresent()) {
                return maybeProduct.get().delete() != null;
            } else {
                return false;
            }
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Price addUnitPrice(String id, String currency, Long amount) {
        try {
            var priceParams = PriceCreateParams.builder()
                    .setCurrency(currency)
                    .setUnitAmount(amount)
                    .setProduct(id)
                    .build();
            return Price.create(priceParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Price addRecurringPrice(String id, String currency, Long amount, SubscriptionPeriod period) {
        try {
            var priceParams = PriceCreateParams.builder()
                    .setCurrency(currency)
                    .setUnitAmount(amount)
                    .setProduct(id)
                    .setRecurring(
                            PriceCreateParams.Recurring.builder()
                                    .setInterval(SubscriptionPeriod.toApi(period))
                                    .build()
                    )
                    .build();
            return Price.create(priceParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
