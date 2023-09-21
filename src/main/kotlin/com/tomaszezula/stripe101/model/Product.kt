package com.tomaszezula.stripe101.model

data class Product(val name: String, val description: String, val prices: Collection<Price>)
