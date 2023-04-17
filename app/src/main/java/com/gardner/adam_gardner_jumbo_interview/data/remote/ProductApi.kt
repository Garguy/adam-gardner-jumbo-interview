package com.gardner.adam_gardner_jumbo_interview.data.remote

import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    
    @GET("products.json")
    suspend fun getProducts(): Response<ProductResponse>
}