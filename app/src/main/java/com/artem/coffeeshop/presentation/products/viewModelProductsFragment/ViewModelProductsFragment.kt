package com.artem.coffeeshop.presentation.products.viewModelProductsFragment

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.domain.GetProductsUseCase
import com.artem.coffeeshop.domain.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ViewModelProductsFragment(private val getProductsData: GetProductsUseCase): ViewModel(),
    CoroutineScope {


    fun setAdapter(productsList: ArrayList<Products>, adapter: RecyclerView){
        launch(Dispatchers.IO) {
            getProductsList(productsList, adapter)
        }
    }


   private suspend fun getProductsList(productsList: ArrayList<Products>, adapter: RecyclerView){
        getProductsData.getProducts(productsList, adapter)
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}