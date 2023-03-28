package com.example.Clothing_store.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
class Product {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var quantity: String? = null
    var price: String? = null
    var category: String? = null
}