package com.gardner.adam_gardner_jumbo_interview.data.cart

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val cartItems = mutableListOf<CartItem>()
    
    override fun getItems(): Flow<List<CartItem>> {
        return flow {
            emit(cartItems.toList())
        }
    }
    
    override fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.product.id == item.product.id }
        if (existingItem == null) {
            cartItems.add(item)
        } else {
            existingItem.quantity += item.quantity
        }
    }
    
    override fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }
    
    override fun clear() {
        cartItems.clear()
    }
}