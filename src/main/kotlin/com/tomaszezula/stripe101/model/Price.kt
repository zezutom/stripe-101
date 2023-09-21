package com.tomaszezula.stripe101.model

data class Price(
    val id: String,
    val productId: String,
    val amount: Long,
    val currency: Currency,
    val isRecurring: Boolean
)
