package com.artem.coffeeshop.presentation.products.viewModelProductsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artem.coffeeshop.domain.GetProductsUseCase
import java.lang.IllegalArgumentException



class ViewModelProductsFragmentFactory(private val getProducts: GetProductsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelProductsFragment::class.java)){
            return ViewModelProductsFragment(getProducts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}