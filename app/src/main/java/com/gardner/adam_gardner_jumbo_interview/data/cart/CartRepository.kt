package com.gardner.adam_gardner_jumbo_interview.data.cart

import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getItems(): Flow<List<CartItem>>
    fun addItem(item: CartItem)
    fun removeItem(item: CartItem)
    fun clear()
}