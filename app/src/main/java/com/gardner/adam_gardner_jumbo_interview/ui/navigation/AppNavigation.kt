package com.gardner.adam_gardner_jumbo_interview.ui.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartViewModel
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.ui.screens.cart.CartScreen
import com.gardner.adam_gardner_jumbo_interview.ui.screens.product_details.ProductDetailsScreen
import com.gardner.adam_gardner_jumbo_interview.ui.screens.product_list.ProductListScreen
import com.gardner.adam_gardner_jumbo_interview.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onGetStartedPress = {
                    navController.navigate("productList")
                }
            )
        }
        composable("productList") {
            ProductListScreen(
                productViewModel = productViewModel,
                cartViewModel = cartViewModel,
                onItemClick = { product ->
                    navController.navigate("productDetails/${product.id}") {
                        launchSingleTop = true
                    }
                },
                navController = navController
            )
        }
        composable(
            route = "productDetails/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductDetailsScreen(
                    productId = productId,
                    viewModel = productViewModel,
                    navController = navController
                )
            } else {
                Toast.makeText(
                    LocalContext.current,
                    "We're sorry, something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        composable(route = "cart") {
            Log.d("AppNav VM", "$cartViewModel")
            CartScreen(navController = navController, cartViewModel = cartViewModel)
        }
    }
}