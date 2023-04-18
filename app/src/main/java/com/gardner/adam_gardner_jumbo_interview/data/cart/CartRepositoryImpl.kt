package com.gardner.adam_gardner_jumbo_interview.data.cart

import android.util.Log
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val cartItems = mutableListOf<CartItem>()
    
    override fun getItems(): List<CartItem> {
        Log.d("ITEMS from REpo", "${cartItems.toList()}")
        return cartItems.toList()
    }
    
    override fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.product.id == item.product.id }
        if (existingItem == null) {
            Log.d("Repositry", "Product is added to cart $item")
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