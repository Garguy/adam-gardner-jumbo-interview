package com.gardner.adam_gardner_jumbo_interview.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController
//    cart: Cart,
//    onItemRemoved: (Product) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") }
            )
        },
        bottomBar = {
            BottomAppBar() {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.navigate("productList") }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "List"
                        )
                    }
                    
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
//        LazyColumn(contentPadding = innerPadding) {
//            items(cart.items) { item ->
//                CartItem(
//                    item = item,
//                    onRemoveClick = { onItemRemoved(item.product) }
//                )
//            }
//
//            item {
//                Text(
//                    text = "Total: ${cart.totalPrice.formatAsCurrency()}",
//                    style = MaterialTheme.typography.h6,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
        
        Text(text = "Cart Screen", modifier = Modifier.padding(innerPadding))
    }
}

//@Composable
//fun CartItem(item: CartItem, onRemoveClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = rememberImagePainter(item.product.image),
//            contentDescription = null,
//            modifier = Modifier.size(80.dp)
//        )
//
//        Column(modifier = Modifier.padding(start = 16.dp)) {
//            Text(
//                text = item.product.title,
//                style = MaterialTheme.typography.h6
//            )
//
//            Text(
//                text = item.quantity.toString(),
//                style = MaterialTheme.typography.body1
//            )
//
//            Text(
//                text = item.totalPrice.formatAsCurrency(),
//                style = MaterialTheme.typography.body1
//            )
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(
//            onClick = onRemoveClick,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Delete,
//                contentDescription = "Remove item"
//            )
//        }
//    }
//}
