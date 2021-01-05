package com.artem.coffeeshop.di

import com.artem.coffeeshop.presentation.account.CreateAccountFragment
import dagger.Component


@Component(modules = [CreateAccountProvedes::class])
interface CreateAccountComponent {

    fun injectCreateAccountFragment(createAccountFragment: CreateAccountFragment)
}