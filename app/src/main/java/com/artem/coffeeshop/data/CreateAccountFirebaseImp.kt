package com.artem.coffeeshop.data

import android.util.Log
import com.artem.coffeeshop.domain.CreateUserFirebase
import com.artem.coffeeshop.domain.User
import com.artem.coffeeshop.utilites.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.CoroutineContext


class CreateAccountFirebaseImp : CreateUserFirebase, CoroutineScope {


    override suspend fun createUser(email: String, password: String, firstName: String): String {

        try {
            AUTH = Firebase.auth


            // создание аккаунта
            AUTH.createUserWithEmailAndPassword(email, password).await()


            // добавление пользователя в бд
            val user = User(email, firstName)
            val userId = CURRENT_UID
            if (userId != null) {
                REF_DATABASE_ROOT.child(NODE_USERS).child(userId).setValue(user)
            }

            Log.i(TAG, "Пользователь ${AUTH.currentUser?.email} сооздан!")

            return "Аккаунт создан!"
            
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.i(TAG, "CreateAccountFirebaseImp: Email занят! " + e.errorCode)
            return e.errorCode
        } catch (e: FirebaseException) {
            Log.i(TAG, "CreateAccountFirebaseImp: Ошибка! " + e.toString())
            return "Ошибка!"
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}