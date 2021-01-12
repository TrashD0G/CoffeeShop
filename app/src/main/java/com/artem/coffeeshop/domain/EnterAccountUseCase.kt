package com.artem.coffeeshop.domain

import com.artem.coffeeshop.data.EnterAccountFirebaseImp

class EnterAccountUseCase(private val enterAccountFirebase: EnterAccountFirebaseImp) {

    suspend fun enterUser(email: String, password: String): String {
        return enterAccountFirebase.signInUser(email, password)
    }
}