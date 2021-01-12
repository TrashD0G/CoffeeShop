package com.artem.coffeeshop.di

import com.artem.coffeeshop.data.CreateAccountFirebaseImp
import com.artem.coffeeshop.domain.CreateAccountUseCase
import com.artem.coffeeshop.presentation.account.viewModelCreateAccount.ViewModelCreateAccountFactory
import dagger.Module
import dagger.Provides

@Module
class CreateAccountProvides {

    @Provides
    fun provideCreateAccountUseCase(createAccountFirebase: CreateAccountFirebaseImp): CreateAccountUseCase {
        return CreateAccountUseCase(createAccountFirebase)
    }

    @Provides
    fun provideCreateAccountFirebaseImp(): CreateAccountFirebaseImp {
        return CreateAccountFirebaseImp()
    }

    @Provides
    fun provideViewModelAccountFactory(createAccountUseCase: CreateAccountUseCase): ViewModelCreateAccountFactory {
        return ViewModelCreateAccountFactory(createAccountUseCase)
    }


}