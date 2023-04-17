package com.gardner.adam_gardner_jumbo_interview.di

import androidx.lifecycle.ViewModel
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductDataSource
import com.gardner.adam_gardner_jumbo_interview.data.remote.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {
    
    @Provides
    fun provideProductData(api: ProductApi): ProductDataSource {
        return ProductDataSource(api)
    }
}