package com.example.Clothing_store.vm

import com.example.Clothing_store.db.ProductDatabase.Companion.getDatabase

import android.app.Application
import com.example.Clothing_store.db.ProductDao
import androidx.lifecycle.LiveData
import com.example.Clothing_store.db.ProductDatabase
import com.example.Clothing_store.tables.Product
import com.example.Clothing_store.tables.Categories

class ProductRepository(application: Application) {
    private val menuDao: ProductDao?
    val menu1Week: LiveData<List<Product?>?>?
    val categories: LiveData<List<Categories?>?>?
    fun insertMenu1(menu1Week: Product?) {
        ProductDatabase.dbWriteExecutor.execute { menuDao!!.insertMenu1Week(menu1Week) }
    }

    fun insertCategories(categories: Categories?) {
        ProductDatabase.dbWriteExecutor.execute { menuDao!!.insertCategories(categories) }
    }

    fun deleteMenu1() {
        menuDao!!.deleteMenu1Week()
    }

    fun deleteCategories() {
        menuDao!!.deleteCategories()
    }

    init {
        menuDao = getDatabase(application.applicationContext)!!.menuDao()
        menu1Week = menuDao!!.LoadAllMenu1Week()
        categories = menuDao.LoadAllCategories()
    }
}