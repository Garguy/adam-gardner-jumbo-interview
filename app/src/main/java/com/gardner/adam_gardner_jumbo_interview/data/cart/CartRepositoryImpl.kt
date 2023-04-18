package com.gardner.adam_gardner_jumbo_interview.data.cart

import android.util.Log
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartPreferences: CartPreferences
) : CartRepository {
    private val cartItems = mutableListOf<CartItem>()
    
    override fun getItems(): List<CartItem> {
        return cartItems.toList()
    }
    
    override fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.product.id == item.product.id }
        if (existingItem == null) {
            cartItems.add(item)
        } else {
            existingItem.quantity += item.quantity
        }
        cartPreferences.saveCartList(cartItems)
    }
    
    override fun removeItem(item: CartItem) {
        cartItems.remove(item)
        cartPreferences.saveCartList(cartItems)
    }
    
    override fun clear() {
        cartItems.clear()
        cartPreferences.saveCartList(cartItems)
    }
}