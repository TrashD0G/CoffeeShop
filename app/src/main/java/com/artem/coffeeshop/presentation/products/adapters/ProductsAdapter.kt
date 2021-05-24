package com.artem.coffeeshop.presentation.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.R
import com.artem.coffeeshop.domain.Products

class ProductsAdapter(private val productsList: ArrayList<Products>): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_item,parent,false)

        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentItem = productsList[position]

        holder.productName.text = currentItem.productName
    }

    override fun getItemCount() = productsList.size

    class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productName = itemView.findViewById<TextView>(R.id.card_products_name)
    }


}