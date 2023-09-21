package com.tomaszezula.stripe101

import com.stripe.exception.StripeException
import com.stripe.param.PriceCreateParams
import com.tomaszezula.stripe101.model.SubscriptionPeriod

fun <T> tryRun(block: () -> T): T {
    return try {
        block()
    } catch (e: StripeException) {
        throw RuntimeException(e)
    }
}

fun SubscriptionPeriod.toInterval(): PriceCreateParams.Recurring.Interval {
    return when (this) {
        SubscriptionPeriod.MONTHLY -> PriceCreateParams.Recurring.Interval.MONTH
        SubscriptionPeriod.YEARLY -> PriceCreateParams.Recurring.Interval.YEAR
    }
}