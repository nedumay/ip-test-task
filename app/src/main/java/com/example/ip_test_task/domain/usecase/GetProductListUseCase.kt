package com.example.ip_test_task.domain.usecase

import com.example.ip_test_task.domain.repository.RepositoryProduct
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: RepositoryProduct) {
    suspend operator fun invoke() = repository.getProductList()
}