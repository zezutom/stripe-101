package com.tomaszezula.stripe101.client;

import static com.tomaszezula.stripe101.util.ErrorHandler.tried;

import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.ProductCreateParams;
import com.tomaszezula.stripe101.model.SubscriptionPeriod;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StripeClient implements IStripeClient {

  @Override
  public Product createProduct(String name, String description) {
    return tried(() -> {
      var productParams = ProductCreateParams.builder()
          .setName(name)
          .setDescription(description)
          .build();
      return Product.create(productParams);
    });
  }

  @Override
  public Product getProduct(String id) {
    return tried(() -> Product.retrieve(id));
  }

  @Override
  public Boolean deleteProduct(String id) {
    return tried(() -> {
      var maybeProduct = Optional.ofNullable(Product.retrieve(id));
      if (maybeProduct.isPresent()) {
        return maybeProduct.get().delete() != null;
      } else {
        return false;
      }
    });
  }

  @Override
  public Price addOneTimePrice(String productId, String currency, Long amount) {
    return tried(() -> {
      var priceParams = PriceCreateParams.builder()
          .setCurrency(currency)
          .setUnitAmount(amount)
          .setProduct(productId)
          .build();
      return Price.create(priceParams);
    });
  }

  @Override
  public Price addRecurringPrice(String productId, String currency, Long amount,
      SubscriptionPeriod period) {
    return tried(() -> {
      var priceParams = PriceCreateParams.builder()
          .setCurrency(currency)
          .setUnitAmount(amount)
          .setProduct(productId)
          .setRecurring(
              PriceCreateParams.Recurring.builder()
                  .setInterval(SubscriptionPeriod.toApi(period))
                  .build()
          )
          .build();
      return Price.create(priceParams);
    });
  }

  @Override
  public PriceCollection getAllPrices(String productId) {
    return tried(() -> {
      var params = PriceListParams
          .builder()
          .setProduct(productId)
          .setActive(true)
          .build();
      return Price.list(params);
    });
  }
}
