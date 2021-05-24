package com.artem.coffeeshop.domain

import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.data.GetProductsFirebaseImp

class GetProductsUseCase(private val getProductsFirebase: GetProductsFirebaseImp) {

    suspend fun getProducts(productsList: ArrayList<Products>, adapter: RecyclerView){
        return getProductsFirebase.getProducts(productsList, adapter)
    }

}