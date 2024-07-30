package com.example.ip_test_task.domain.usecase

import com.example.ip_test_task.domain.model.ProductInfo
import com.example.ip_test_task.domain.repository.RepositoryProduct
import javax.inject.Inject

class DeleteProductItemUseCase @Inject constructor(private val repository: RepositoryProduct) {
    suspend operator fun invoke(productItem: ProductInfo) = repository.deleteProduct(productItem)
}