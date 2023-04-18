package com.gardner.adam_gardner_jumbo_interview.di

import android.content.Context
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartPreferences
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartRepository
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartRepositoryImpl
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductDataSource
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductRepository
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductRepositoryImpl
import com.gardner.adam_gardner_jumbo_interview.data.remote.ProductApi
import com.gardner.adam_gardner_jumbo_interview.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideProductApi(moshi: Moshi): ProductApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ProductApi::class.java)
    }
    
    @Provides
    fun provideProductRepository(dataSource: ProductDataSource): ProductRepository {
        return ProductRepositoryImpl(dataSource)
    }
    
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context, moshi: Moshi): CartPreferences {
        return CartPreferences(context, moshi)
    }
    
    @Provides
    fun provideCartRepository(cartPreferences: CartPreferences): CartRepository {
        return CartRepositoryImpl(cartPreferences)
    }
}