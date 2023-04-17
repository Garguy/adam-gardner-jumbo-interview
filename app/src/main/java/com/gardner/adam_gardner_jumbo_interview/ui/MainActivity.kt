package com.gardner.adam_gardner_jumbo_interview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.ui.navigation.AppNavigation
import com.gardner.adam_gardner_jumbo_interview.ui.theme.AdamgardnerjumbointerviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdamgardnerjumbointerviewTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdamgardnerjumbointerviewTheme {
        Greeting("Android")
    }
}