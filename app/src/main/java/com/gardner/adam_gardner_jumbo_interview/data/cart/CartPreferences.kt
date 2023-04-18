package com.gardner.adam_gardner_jumbo_interview.data.cart

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class CartPreferences @Inject constructor(context: Context, private val moshi: Moshi) {
    
    private val sharedPreferences =
        context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
    
    fun saveCartList(cartList: List<CartItem>) {
        val editor = sharedPreferences.edit()
        val adapter =
            moshi.adapter<List<CartItem>>(
                Types.newParameterizedType(
                    List::class.java,
                    CartItem::class.java
                )
            )
        val json = adapter.toJson(cartList)
        editor.putString("cart_list", json)
        editor.apply()
    }
    
    fun getCartList(): List<CartItem> {
        val json = sharedPreferences.getString("cart_list", null)
        return if (json != null) {
            val adapter = moshi.adapter<List<CartItem>>(
                Types.newParameterizedType(
                    List::class.java,
                    CartItem::class.java
                )
            )
            adapter.fromJson(json) ?: emptyList()
        } else {
            emptyList()
        }
    }
}