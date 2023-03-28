package com.example.Clothing_store.vm


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.Clothing_store.tables.Categories
import com.example.Clothing_store.tables.Product

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    val menu1Week: LiveData<List<Product?>?>?
    val categories: LiveData<List<Categories?>?>?

    var repository: ProductRepository

    fun insertMenu1(menu1Week: Product?) {
        repository.insertMenu1(menu1Week)
    }

    fun insertCategories(categories: Categories?) {
        repository.insertCategories(categories)
    }

    fun deleteMenu1() {
        repository.deleteMenu1()
    }

    fun deleteCategories() {
        repository.deleteCategories()
    }

    init {
        repository = ProductRepository(application)
        menu1Week = repository.menu1Week
        categories = repository.categories
    }
}