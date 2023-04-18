package com.gardner.adam_gardner_jumbo_interview.ui.screens.product_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.gardner.adam_gardner_jumbo_interview.R
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartItem
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    onAddToCart: (CartItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onItemClick(product) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = product.imageInfo.primaryView.firstOrNull()?.url,
                contentDescription = product.title,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "An error occurred loading image"
                    )
                }
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                
                Text(text = product.title ?: "Unknown", fontWeight = FontWeight.Bold)
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(text = product.prices.price.amount.toString())
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Button(
                    onClick = {
                        onAddToCart(CartItem(product = product, quantity = 1))
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Add to cart")
                }
            }
        }
    }
}