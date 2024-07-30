package com.example.ip_test_task.di

import android.content.Context
import androidx.room.Room
import com.example.ip_test_task.data.database.AppDatabase
import com.example.ip_test_task.data.database.ProductInfoDao
import com.example.ip_test_task.data.mapper.MapperProduct
import com.example.ip_test_task.data.repository.RepositoryProductImpl
import com.example.ip_test_task.domain.repository.RepositoryProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    private var db: AppDatabase? = null
    private const val DATABASE_NAME = "data.db"
    private val LOCK = Any()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        synchronized(LOCK) {
            db?.let { return it }
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
            db = instance
            return instance
        }
    }

    @Provides
    fun provideProductInfoDao(appDatabase: AppDatabase) = appDatabase.productInfoDao()

    @Provides
    fun provideRepositoryProduct(
        mapperProduct: MapperProduct,
        productInfoDao: ProductInfoDao
    ): RepositoryProduct {
        return RepositoryProductImpl(mapperProduct, productInfoDao)
    }

}