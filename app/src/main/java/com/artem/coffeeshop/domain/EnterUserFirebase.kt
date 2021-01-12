package com.artem.coffeeshop.domain

interface EnterUserFirebase {
    suspend fun signInUser(email: String, password: String): String
}