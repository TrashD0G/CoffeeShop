package com.artem.coffeeshop.di

import com.artem.coffeeshop.data.GetProductsFirebaseImp
import com.artem.coffeeshop.domain.GetProductsUseCase
import com.artem.coffeeshop.presentation.products.viewModelProductsFragment.ViewModelProductsFragmentFactory
import dagger.Module
import dagger.Provides

@Module
class ProductsProvides {

    @Provides
    fun provideGetProductsUseCase(getProductsFirebase: GetProductsFirebaseImp): GetProductsUseCase {
        return GetProductsUseCase(getProductsFirebase)
    }

    @Provides
    fun provideGetProductsFirebaseImp(): GetProductsFirebaseImp {
        return GetProductsFirebaseImp()
    }

    @Provides
    fun provideViewModelProductsFactory(productsUseCase: GetProductsUseCase): ViewModelProductsFragmentFactory {
        return ViewModelProductsFragmentFactory(productsUseCase)
    }
}