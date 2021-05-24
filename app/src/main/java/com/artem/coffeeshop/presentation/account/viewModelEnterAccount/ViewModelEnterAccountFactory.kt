package com.artem.coffeeshop.presentation.account.viewModelEnterAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artem.coffeeshop.domain.EnterAccountUseCase
import java.lang.IllegalArgumentException

class ViewModelEnterAccountFactory(private val enterAccount: EnterAccountUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelEnterAccount::class.java)){
            return ViewModelEnterAccount(enterAccount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}