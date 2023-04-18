package com.gardner.adam_gardner_jumbo_interview.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun CartListItem(
    item: CartItem,
    onRemoveItem: (CartItem) -> Unit,
    onClearCart: () -> Unit,
    index: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = item.product.imageInfo.primaryView.firstOrNull()?.url,
                contentDescription = item.product.title,
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
            
            Column(
                modifier = Modifier.weight(1f, true)
            ) {
                Text(text = item.product.title ?: "Unknown", fontWeight = FontWeight.Bold)
                Text(text = item.product.prices.price.amount.toString())
            }
            IconButton(onClick = { onRemoveItem(item) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove item")
            }
            IconButton(onClick = { onClearCart()}) {
                Icon(Icons.Default.Clear, contentDescription = "Clear Cart")
            }
        }
    }
}