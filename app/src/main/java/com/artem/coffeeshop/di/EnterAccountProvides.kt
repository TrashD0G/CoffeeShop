package com.artem.coffeeshop.di

import com.artem.coffeeshop.data.EnterAccountFirebaseImp
import com.artem.coffeeshop.domain.EnterAccountUseCase
import com.artem.coffeeshop.presentation.account.viewModelEnterAccount.ViewModelEnterAccountFactory
import dagger.Module
import dagger.Provides

@Module
class EnterAccountProvides {

    @Provides
    fun provideEnterAccountUseCase(enterAccountFirebase: EnterAccountFirebaseImp): EnterAccountUseCase {
        return EnterAccountUseCase(enterAccountFirebase)
    }

    @Provides
    fun provideEnterAccountFirebaseImp(): EnterAccountFirebaseImp {
        return EnterAccountFirebaseImp()
    }

    @Provides
    fun provideViewModelEnterFactory(enterAccountUseCase: EnterAccountUseCase): ViewModelEnterAccountFactory {
        return ViewModelEnterAccountFactory(enterAccountUseCase)
    }
}