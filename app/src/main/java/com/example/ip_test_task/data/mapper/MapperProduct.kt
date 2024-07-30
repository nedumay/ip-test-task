package com.example.ip_test_task.data.mapper

import com.example.ip_test_task.data.database.ProductInfoDbModel
import com.example.ip_test_task.domain.model.ProductInfo
import javax.inject.Inject

/**
 * @author Samim
 * Класс маппера для передачи данных между data и domain
 */
class MapperProduct @Inject constructor() {

    fun mapDbModelToEntity(dbModel: ProductInfoDbModel) =
        ProductInfo(
            id = dbModel.id,
            name = dbModel.name,
            time = dbModel.time,
            tags = dbModel.tags,
            amount = dbModel.amount
        )

    fun mapEntityToDbModel(entity: ProductInfo) =
        ProductInfoDbModel(
            id = entity.id,
            name = entity.name,
            time = entity.time,
            tags = entity.tags,
            amount = entity.amount
        )
}