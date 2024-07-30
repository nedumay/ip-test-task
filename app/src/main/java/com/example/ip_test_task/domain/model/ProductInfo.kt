package com.example.ip_test_task.domain.model

/**
 * @author Samim
 * Класс продукт для работы с use case
 */
data class ProductInfo(
    val name: String,
    val time: Int,
    val tags: String,
    var amount: Int,
    var id: Int = UNDEFIEND_ID
) {
    companion object {
        const val UNDEFIEND_ID = 0
    }
}
