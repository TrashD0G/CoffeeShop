package com.artem.coffeeshop.domain

interface CreateUserFirebase {
    suspend fun createUser(email: String, password: String): String
}