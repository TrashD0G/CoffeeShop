package com.artem.coffeeshop.domain

import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.presentation.products.adapters.ProductsAdapter

interface GetProductsFirebase {
    suspend fun getProducts(productsList: ArrayList<Products>, adapter: RecyclerView)
}