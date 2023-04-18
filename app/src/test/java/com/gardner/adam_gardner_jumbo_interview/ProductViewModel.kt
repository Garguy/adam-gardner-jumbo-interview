package com.gardner.adam_gardner_jumbo_interview

import com.gardner.adam_gardner_jumbo_interview.data.product.ProductRepository
import com.gardner.adam_gardner_jumbo_interview.data.product.ProductViewModel
import com.gardner.adam_gardner_jumbo_interview.data.remote.dto.Product
import com.gardner.adam_gardner_jumbo_interview.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ProductViewModelTest {
    
    private lateinit var viewModel: ProductViewModel
    
    private val testDispatcher = UnconfinedTestDispatcher()
    
    private val mockRepository: ProductRepository = mock()
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductViewModel(mockRepository)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `getProducts returns success`() = runTest {
        val products = listOf(mock(Product::class.java))
        val expectedResult = Resource.Success(products)
        
        `when`(mockRepository.getProducts()).thenReturn(expectedResult)
        
        viewModel.getProducts()
        
        delay(500)
        
        val actualResult = viewModel.products.first()
        verify(mockRepository).getProducts()
        
        assertEquals(expectedResult, actualResult)
    }
    
    @Test
    fun `getProducts returns error`() = runTest {
        val expectedErrorMessage = "Failed to fetch products"
        val expectedResult = Resource.Error(expectedErrorMessage)
        
        `when`(mockRepository.getProducts()).thenReturn(expectedResult)
        
        viewModel.getProducts()
        
        val actualResult = viewModel.products.first()
        val actualProducts = actualResult as Resource.Error
        
        assertTrue(actualProducts.message!!.isNotEmpty())
        assertTrue(actualProducts.message == expectedErrorMessage)
    }
}
