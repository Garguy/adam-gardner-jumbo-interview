package com.gardner.adam_gardner_jumbo_interview.data.cart

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
    
    private val _itemCount = MutableStateFlow<Map<String, Int>>(emptyMap())
    val itemCount: StateFlow<Map<String, Int>> = _itemCount
    
    init {
        val savedCart = cartPreferences.getCartList()
        
        if (savedCart.isNotEmpty()) {
            _items.value = savedCart
            _itemCount.value = savedCart.groupingBy { it.product.id }
                .eachCount()
        } else {
            viewModelScope.launch {
                _items.value = cartRepository.getItems()
                _itemCount.value = _items.value.groupingBy { it.product.id }
                    .eachCount()
                cartPreferences.saveCartList(_items.value)
            }
        }
    }
    
    fun addItem(item: CartItem) {
        cartRepository.addItem(item)
        viewModelScope.launch {
            _items.value = cartRepository.getItems()
            _itemCount.value = _items.value.groupingBy { it.product.id }
                .eachCount()
        }
    }
    
    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeItem(item)
            _items.value = cartRepository.getItems()
            _itemCount.value = _items.value.groupingBy { it.product.id }
                .eachCount()
        }
    }
    
    fun clear() {
        viewModelScope.launch {
            cartRepository.clear()
            _items.value = emptyList()
            _itemCount.value = emptyMap()
        }
    }
}