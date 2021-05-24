package com.artem.coffeeshop.data


import android.util.Log
import com.artem.coffeeshop.domain.EnterUserFirebase
import com.artem.coffeeshop.utilites.AUTH
import com.artem.coffeeshop.utilites.TAG
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.CoroutineContext

class EnterAccountFirebaseImp : EnterUserFirebase, CoroutineScope {



    override suspend fun signInUser(email: String, password: String): String {

        try {
            AUTH.signInWithEmailAndPassword(email, password).await()
            val user = AUTH.currentUser
            Log.i(TAG,"EnterAccountFirebaseImp: Вход под ${user?.email} !")
            return "Выполнен вход!"
        } catch (e: FirebaseException){
            Log.i(TAG, "EnterAccountFirebaseImp: Ошибка! $e")
            return "Ошибка!"
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}