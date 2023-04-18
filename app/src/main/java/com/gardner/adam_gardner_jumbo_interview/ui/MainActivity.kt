package com.gardner.adam_gardner_jumbo_interview.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartViewModel
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.ui.navigation.AppNavigation
import com.gardner.adam_gardner_jumbo_interview.ui.theme.AdamgardnerjumbointerviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdamgardnerjumbointerviewTheme {
                Log.d("Main Activity VM", "$cartViewModel")
                AppNavigation(
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}