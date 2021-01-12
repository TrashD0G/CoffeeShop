package com.artem.coffeeshop.presentation.account.viewModelCreateAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artem.coffeeshop.domain.CreateAccountUseCase
import java.lang.IllegalArgumentException


class ViewModelCreateAccountFactory (private val createAccount: CreateAccountUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelCreateAccount::class.java)){
            return ViewModelCreateAccount(createAccount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}