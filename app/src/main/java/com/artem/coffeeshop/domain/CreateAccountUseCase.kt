package com.artem.coffeeshop.domain

import com.artem.coffeeshop.data.CreateAccountFirebaseImp

class CreateAccountUseCase(private val createAccountFirebase: CreateAccountFirebaseImp) {

    suspend fun createUser(email: String, password: String): String {
        return createAccountFirebase.createUser(email, password)
    }

}