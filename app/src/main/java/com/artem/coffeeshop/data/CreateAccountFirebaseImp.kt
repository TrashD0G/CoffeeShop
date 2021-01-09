package com.artem.coffeeshop.data

import android.util.Log
import com.artem.coffeeshop.domain.CreateUserFirebase
import com.artem.coffeeshop.presentation.mainScreen.TAG
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.CoroutineContext


class CreateAccountFirebaseImp() : CreateUserFirebase, CoroutineScope {

    lateinit var auth: FirebaseAuth


    override suspend fun createUser(email: String, password: String): String {

        try {
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password).await()
            return "Аккаунт создан!"

        } catch (e: FirebaseAuthUserCollisionException) {
            Log.i(TAG, "createUserWithEmail: Email занят! " + e.errorCode)
            return e.errorCode
        } catch (e: FirebaseException) {
            Log.i(TAG, "Ошибка! " + e.toString())
            return "Ошибка!"
        }


    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}