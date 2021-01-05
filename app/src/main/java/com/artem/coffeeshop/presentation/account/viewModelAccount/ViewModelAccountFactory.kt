package com.artem.coffeeshop.presentation.account.viewModelAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artem.coffeeshop.domain.CreateAccountUseCase
import java.lang.IllegalArgumentException


class ViewModelAccountFactory (private val createAccount: CreateAccountUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelAccount::class.java)){
            return ViewModelAccount(createAccount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}