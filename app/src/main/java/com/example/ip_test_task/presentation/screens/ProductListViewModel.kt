package com.example.ip_test_task.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ip_test_task.common.Resource
import com.example.ip_test_task.domain.model.ProductInfo
import com.example.ip_test_task.domain.usecase.DeleteProductItemUseCase
import com.example.ip_test_task.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val deleteProductItemUseCase: DeleteProductItemUseCase,
    private val updateProductItemUseCase: DeleteProductItemUseCase
) : ViewModel() {

    private val _productInfo = MutableStateFlow<Resource<List<ProductInfo>>>(Resource.Loading)
    val productInfo = _productInfo.asStateFlow()

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            try {
                _productInfo.value = Resource.Loading
                val data = getProductListUseCase.invoke()
                _productInfo.value = Resource.Success(data)
            } catch (e: Exception) {
                _productInfo.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun deleteProductItem(productItem: ProductInfo) {
        viewModelScope.launch {
            deleteProductItemUseCase.invoke(productItem)
        }
    }

    fun updateProductItem(productItem: ProductInfo) {
        viewModelScope.launch {
            updateProductItemUseCase.invoke(productItem)
        }
    }
}