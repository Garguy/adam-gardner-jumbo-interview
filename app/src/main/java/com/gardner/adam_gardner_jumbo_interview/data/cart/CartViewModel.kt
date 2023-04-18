package com.gardner.adam_gardner_jumbo_interview.data.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val cartPreferences: CartPreferences
) : ViewModel() {
    
    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items
    
    init {
        val savedCart = cartPreferences.getCartList()
        
        if (savedCart.isNotEmpty()) {
            _items.value = savedCart
        } else {
            viewModelScope.launch {
                _items.value = cartRepository.getItems()
                cartPreferences.saveCartList(_items.value)
            }
        }
    }
    
    fun addItem(item: CartItem) {
        Log.d("MV addItem", item.toString())
        cartRepository.addItem(item)
        viewModelScope.launch {
            _items.value = cartRepository.getItems()
        }
    }
    
    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeItem(item)
            _items.value = cartRepository.getItems()
        }
    }
    
    fun clear() {
        viewModelScope.launch {
            cartRepository.clear()
            _items.value = emptyList()
        }
    }
}