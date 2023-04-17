package com.gardner.adam_gardner_jumbo_interview.data.product

import com.gardner.adam_gardner_jumbo_interview.data.remote.ProductApi
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.utils.Resource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    
    override suspend fun getProducts(): Resource<List<Product>> {
        return try {
            val response = productDataSource.getProducts()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}