package com.example.ip_test_task.common

/**
 * @author Samim
 * Класс ресурса для обработки состояния приложения
 */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error(val message: String) : Resource<Nothing>()
}