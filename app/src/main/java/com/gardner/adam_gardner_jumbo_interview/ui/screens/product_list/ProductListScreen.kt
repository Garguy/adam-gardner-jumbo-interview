package com.gardner.adam_gardner_jumbo_interview.ui.screens.product_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartViewModel
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onItemClick: (Product) -> Unit,
    navController: NavController
) {
    val products = productViewModel.products.collectAsState()
    
    Log.d("ProductList VM", "$cartViewModel")
    
    val itemsInCart by cartViewModel.items.collectAsState()
    
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            productViewModel.getProducts()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product List") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomAppBar() {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.navigate("productList") }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "List"
                            )
                            Text(text = "LIST", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    
                    BadgedBox(badge = {
                        Text(
                            text = "${itemsInCart.size}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }) {
                        IconButton(onClick = { navController.navigate("cart") }) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = "Cart"
                                )
                                Text(text = "CART", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (val results = products.value) {
                is Resource.Success -> {
                    LazyColumn {
                        items(results.data) { product ->
                            ProductListItem(
                                product = product,
                                onItemClick = onItemClick,
                                onAddToCart = { cartItem ->
                                    cartViewModel.addItem(cartItem)
                                }
                            )
                        }
                    }
                }
                
                is Resource.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
                
                is Resource.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = results.message ?: "We're sorry, ¯\\_(ツ)_/¯",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}