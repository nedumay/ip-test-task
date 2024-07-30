package com.example.ip_test_task.presentation.screens

import com.example.ip_test_task.domain.model.ProductInfo

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<ProductInfo> = emptyList(),
    val error: String = ""
)
