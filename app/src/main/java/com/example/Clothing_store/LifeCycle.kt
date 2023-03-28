package com.example.Clothing_store

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.example.Clothing_store.tables.Product
import com.example.Clothing_store.tables.Categories
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class LifeCycle : LifecycleObserver, LifecycleOwner {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun initViewModel() {

        MainActivity.version = MainActivity.sharedPreferences!!.getFloat("Version", 0f)

        MainActivity.viewModel?.menu1Week?.observe(this,
            { menu1Weeks: List<Product>? -> MainActivity.product = menu1Weeks
                if (check) {
                    MainActivity.setFM()
                } else if (RealtimeDB.i == menu1Weeks!!.size && menu1Weeks.isNotEmpty()) {
                    MainActivity.setFM()
                }
                Log.i("LIFECYCLE", "initViewModel " + menu1Weeks!!.size.toString())
            } as (List<Product?>?) -> Unit)
        MainActivity.viewModel?.categories?.observe(this,
            { categories: List<Categories>? -> MainActivity.categories = categories } as (List<Categories?>?) -> Unit)

        mDataBase = FirebaseDatabase.getInstance()
        ref = mDataBase!!.getReference("Shop")

        if (MainActivity.version != 0f) {
            if (!MainActivity.checkIt!!) {
                check = true
                Log.i("FirstLaunch", "No Ethernet")
            } else {
                RealtimeDB.dataFromDB
            }
        } else {
            if (!MainActivity.checkIt!!) {
                Log.i("FirstLaunch", "FirstLaunch Without Ethernet")
            } else {
                RealtimeDB.dataFromDB
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun savePref() {
        MainActivity.sharedPreferences?.edit()?.putFloat(
            "Version",
            MainActivity.version
        )?.apply()
    }

    override fun getLifecycle(): Lifecycle {
        return MainActivity.lifecycle!!
    }

    companion object {
        var mDataBase: FirebaseDatabase? = null
        @JvmStatic
        var ref: DatabaseReference? = null
        var check = false
    }
}