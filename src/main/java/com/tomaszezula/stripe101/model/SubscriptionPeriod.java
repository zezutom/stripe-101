package com.tomaszezula.stripe101.model;

import com.stripe.param.PriceCreateParams;

public enum SubscriptionPeriod {
    MONTHLY, YEARLY;

    public static PriceCreateParams.Recurring.Interval toApi(SubscriptionPeriod period) {
        return switch (period) {
            case MONTHLY -> PriceCreateParams.Recurring.Interval.MONTH;
            case YEARLY -> PriceCreateParams.Recurring.Interval.YEAR;
        };
    }

}
