package com.tomaszezula.stripe101.service;

import com.stripe.model.PriceCollection;
import com.tomaszezula.stripe101.client.IStripeClient;
import com.tomaszezula.stripe101.model.Price;
import com.tomaszezula.stripe101.model.Product;
import com.tomaszezula.stripe101.model.SubscriptionPeriod;
import com.tomaszezula.stripe101.util.Result;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return Result.some(Product.fromApi(product, Collections.emptyList()));
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
        var priceCollection = stripeClient.getAllPrices(product.getId());
        var prices = Optional.ofNullable(priceCollection)
            .map(PriceCollection::getData)
            .orElseGet(Collections::emptyList);
        return Result.some(Product.fromApi(product, prices));
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
      var price = stripeClient.addOneTimePrice(id, currency, amount);
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
  public Result<Price> addRecurringPrice(String id, String currency, Long amount,
      SubscriptionPeriod period) {
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
