package com.artem.coffeeshop.di

import com.artem.coffeeshop.presentation.account.CreateAccountFragment
import com.artem.coffeeshop.presentation.account.EnterAccountFragment
import com.artem.coffeeshop.presentation.products.ProductsFragment
import dagger.Component


@Component(modules = [CreateAccountProvides::class, EnterAccountProvides::class, ProductsProvides::class])
interface AppComponent {

    fun injectCreateAccountFragment(createAccountFragment: CreateAccountFragment)
    fun injectEnterAccountFragment(enterAccountFragment: EnterAccountFragment)
    fun injectProductsFragment(ProductsFragment: ProductsFragment)

}