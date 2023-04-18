package com.gardner.adam_gardner_jumbo_interview.data.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    
    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>>
        get() = _items
    
    fun addItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.addItem(item)
        }
    }
    
    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeItem(item)
        }
    }
    
    fun clear() {
        viewModelScope.launch {
            cartRepository.clear()
        }
    }
}