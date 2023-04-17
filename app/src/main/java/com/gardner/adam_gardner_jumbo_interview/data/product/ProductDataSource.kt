package com.gardner.adam_gardner_jumbo_interview.data.product

import com.gardner.adam_gardner_jumbo_interview.data.remote.ProductApi
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import javax.inject.Inject

class ProductDataSource @Inject constructor(
    private val api: ProductApi
) {
    suspend fun getProducts(): List<Product> {
        val response = api.getProducts()
        return if (response.isSuccessful) {
            response.body()?.products?.map { productResults ->
                Product(
                    available = productResults.available,
                    id = productResults.id,
                    imageInfo = productResults.imageInfo,
                    nixProduct = productResults.nixProduct,
                    prices = productResults.prices,
                    productType = productResults.productType,
                    quantity = productResults.quantity,
                    reason = productResults.reason,
                    title = productResults.title,
                    topLevelCategory = productResults.topLevelCategory,
                    topLevelCategoryId = productResults.topLevelCategoryId,
                    unavailabilityReason = productResults.unavailabilityReason
                )
            } ?: emptyList()
        } else {
            emptyList()
        }
    }
}