package com.gardner.adam_gardner_jumbo_interview.data.product

import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.utils.Resource

interface ProductRepository {
    suspend fun getProducts(): Resource<List<Product>>
}