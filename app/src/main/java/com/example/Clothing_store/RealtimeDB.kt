package com.example.Clothing_store

import android.util.Log
import com.example.Clothing_store.MainActivity.Companion.viewModel

import com.example.Clothing_store.LifeCycle.Companion.ref

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.example.Clothing_store.tables.Categories
import com.example.Clothing_store.tables.Product

object RealtimeDB {
    var i = 0
    val dataFromDB: Unit
        get() {
            Log.i("REALTIMEDB", "getDataFromDB")
            val vListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var dataSnapshot = dataSnapshot

                        //Log.i("REALTIMEDB", "LOAD DB")
                        viewModel!!.deleteCategories()
                        viewModel!!.deleteMenu1()
                        //version = dataSnapshot.child("/Version").value.toString().toDouble().toFloat()
                        var categories = Categories()
                        categories.nextCategories = i
                        viewModel!!.insertCategories(categories)
                        dataSnapshot = dataSnapshot.child("/Categories")
                        for (ds in dataSnapshot.children) {
                            Log.i("REALTIMEDB", ds.key!!)
                            for (ds1 in ds.children) {
                                addDataToRoomDB(Product(), ds.key, ds1)
                                i++
                            }
                            categories = Categories()
                            categories.nextCategories = i
                            viewModel!!.insertCategories(categories)
                            Log.i("REALTIMEDB", java.lang.String.valueOf(categories.nextCategories))
                        }
                    //}
                    //Log.i("REALTIMEDB", "MainActivity/setFM")
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }
            ref!!.addValueEventListener(vListener)
        }

    private fun addDataToRoomDB(product: Product, category: String?, ds: DataSnapshot) {
        when (category) {
            "T-short" -> product.category = "Футболки"
            "Short" -> product.category = "Шорты"
            "Bags" -> product.category = "Сумки"
            "Shoes" -> product.category = "Обувь"
        }
        product.name = ds.child("Name").value.toString()
        product.quantity = ds.child("Quantity").value.toString()
        product.price = ds.child("Price").value.toString()
        viewModel!!.insertMenu1(product)
    }
}