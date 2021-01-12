package com.artem.coffeeshop.data


import android.util.Log
import com.artem.coffeeshop.domain.EnterUserFirebase
import com.artem.coffeeshop.presentation.mainScreen.TAG
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.CoroutineContext

class EnterAccountFirebaseImp : EnterUserFirebase, CoroutineScope {

    lateinit var auth: FirebaseAuth

    override suspend fun signInUser(email: String, password: String): String {

        try {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            Log.i(TAG,"EnterAccountFirebaseImp: Вход под ${user?.email} !")
            return "Выполнен вход!"
        } catch (e: FirebaseException){
            Log.i(TAG, "EnterAccountFirebaseImp: Ошибка! " + e.toString())
            return "Ошибка!"
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}