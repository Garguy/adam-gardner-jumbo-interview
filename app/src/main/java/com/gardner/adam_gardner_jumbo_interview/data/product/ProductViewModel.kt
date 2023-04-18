package com.gardner.adam_gardner_jumbo_interview.data.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    
    private val _products = MutableStateFlow<Resource<List<Product>>>(Resource.Loading)
    val products: StateFlow<Resource<List<Product>>> = _products
    
    private val productById = mutableMapOf<String, Product>()
    
    fun getProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _products.value = Resource.Loading
                try {
                    val result = repository.getProducts()
                    if (result is Resource.Success) {
                        result.data.forEach { product ->
                            productById[product.id] = product
                        }
                    }
                    _products.value = result
                } catch (e: Exception) {
                    _products.value = Resource.Error("Failed to fetch products: ${e.message}")
                }
            }
        }
    }
    
    fun getProductById(productId: String): Product? {
        return productById[productId]
    }
}