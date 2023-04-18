package com.gardner.adam_gardner_jumbo_interview

import com.gardner.adam_gardner_jumbo_interview.data.cart.CartItem
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartPreferences
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartRepository
import com.gardner.adam_gardner_jumbo_interview.data.cart.CartViewModel
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {
    
    private lateinit var viewModel: CartViewModel
    
    private val testDispatcher = UnconfinedTestDispatcher()
    
    private var cartRepository: CartRepository = mock()
    
    private var cartPreferences: CartPreferences = mock()
    
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CartViewModel(cartRepository, cartPreferences)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `test addItem`() = runTest {
        
        val item = CartItem(mock(Product::class.java), 1)
        val expectedList = listOf(item)
        
        `when`(cartRepository.getItems()).thenReturn(expectedList)
        viewModel.addItem(item)
        
        val result = viewModel.items.first()
        assertEquals(expectedList, result)
    }
    
    @Test
    fun `test removeItem`() = runTest {
        
        val item = CartItem(mock(Product::class.java), 1)
        val expectedList = emptyList<CartItem>()
        
        `when`(cartRepository.getItems()).thenReturn(emptyList())
        viewModel.removeItem(item)
        
        val result = viewModel.items.first()
        assertEquals(expectedList, result)
    }
    
    @Test
    fun `test clear`() = runTest {
        
        val expectedList = emptyList<CartItem>()
        
        viewModel.clear()
        
        val result = viewModel.items.first()
        assertEquals(expectedList, result)
    }
}
