package com.gardner.adam_gardner_jumbo_interview.data.cart

import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product

data class CartItem(
    val product: Product,
    var quantity: Int
)
