package com.artem.coffeeshop.di

import com.artem.coffeeshop.presentation.account.CreateAccountFragment
import com.artem.coffeeshop.presentation.account.EnterAccountFragment
import dagger.Component


@Component(modules = [CreateAccountProvides::class, EnterAccountProvides::class])
interface AccountComponent {
    fun injectCreateAccountFragment(createAccountFragment: CreateAccountFragment)
    fun injectEnterAccountFragment(enterAccountFragment: EnterAccountFragment)
}