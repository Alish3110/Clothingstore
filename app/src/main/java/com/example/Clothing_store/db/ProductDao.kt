package com.example.Clothing_store.db

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.Clothing_store.tables.Product
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.Clothing_store.tables.Categories

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun LoadAllMenu1Week(): LiveData<List<Product?>?>?

    @Query("DELETE FROM Product")
    fun deleteMenu1Week()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenu1Week(product: Product?)

    @Query("SELECT * FROM Categories")
    fun LoadAllCategories(): LiveData<List<Categories?>?>?

    @Query("DELETE FROM Categories")
    fun deleteCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: Categories?)
}