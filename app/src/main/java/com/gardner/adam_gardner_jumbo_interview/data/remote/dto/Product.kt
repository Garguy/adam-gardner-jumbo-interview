package com.gardner.adam_gardner_jumbo_interview.data.remote.dto

data class Product(
    val available: Boolean?,
    val id: String,
    val imageInfo: ImageInfo,
    val nixProduct: Boolean?,
    val prices: Prices,
    val productType: String?,
    val quantity: String?,
    val reason: String?,
    val title: String?,
    val topLevelCategory: String?,
    val topLevelCategoryId: String?,
    val unavailabilityReason: String?
)