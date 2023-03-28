package com.example.Clothing_store

import android.util.Log

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

import android.widget.TextView
import com.example.Clothing_store.tables.Product
import java.lang.IllegalStateException

class RecMenuAdapter(list: List<Product>) : RecyclerView.Adapter<RecMenuAdapter.ViewHolder>() {
    private val mValues: List<Product>
    private val TYPE_ITEM1 = 0
    private val TYPE_ITEM2 = 1
    private val TYPE_ITEM3 = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        return when (viewType) {
            TYPE_ITEM1 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.menu_body, parent, false)
                ViewHolder(view)
            }
            TYPE_ITEM2 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.menu_head, parent, false)
                ViewHolder(view)
            }
            TYPE_ITEM3 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.menu_foot, parent, false)
                ViewHolder(view)
            }
            else -> throw IllegalStateException("Unexpected value: $viewType")
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = getItemViewType(position)
        when (type) {
            TYPE_ITEM1, TYPE_ITEM3 -> {
                holder.name.text = mValues[position].name
                holder.quantity.text = mValues[position].quantity
                holder.price.text = mValues[position].price
            }
            TYPE_ITEM2 -> {
                holder.category.text = mValues[position].category
                holder.name.text = mValues[position].name
                holder.quantity.text = mValues[position].quantity
                holder.price.text = mValues[position].price
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val categories = mValues[position].category
        for (i in MainActivity.categories!!.indices) {
            if (MainActivity.categories!![i].nextCategories == position) {
                return TYPE_ITEM2
            }
        }
        return if (position + 1 == itemCount || categories != mValues[position + 1].category) {
            TYPE_ITEM3
        } else TYPE_ITEM1

        // По default'y возвращаем основной view.
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    class ViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        var name: TextView
        var quantity: TextView
        var price: TextView
        var category: TextView

        init {
            price = mView.findViewById(R.id.price)
            category = mView.findViewById(R.id.category)
            name = mView.findViewById(R.id.name)
            quantity = mView.findViewById(R.id.quantity)
        }
    }

    init {
        Log.i("RECMENUADAPTER", " RecMenuAdapter")
        mValues = list
        Log.i("RECMENUADAPTER", mValues.size.toString())
    }
}