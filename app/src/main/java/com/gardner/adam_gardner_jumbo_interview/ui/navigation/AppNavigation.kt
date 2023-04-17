package com.gardner.adam_gardner_jumbo_interview.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.ui.screens.product_details.ProductDetailsScreen
import com.gardner.adam_gardner_jumbo_interview.ui.screens.product_list.ProductListScreen

@Composable
fun AppNavigation(
    viewModel: ProductViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            ProductListScreen(
                viewModel = viewModel,
                onItemClick = { product ->
                    navController.navigate("productDetails/${product.id}") {
                        launchSingleTop = true
                    }
                }
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
                    viewModel = viewModel,
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
    }
}