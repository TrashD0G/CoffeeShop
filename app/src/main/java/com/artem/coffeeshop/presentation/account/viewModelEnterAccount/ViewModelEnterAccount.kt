package com.artem.coffeeshop.presentation.account.viewModelEnterAccount

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.coffeeshop.domain.EnterAccountUseCase
import com.artem.coffeeshop.presentation.mainScreen.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class ViewModelEnterAccount(private val enterAccount: EnterAccountUseCase):ViewModel(),
    CoroutineScope {

    private val _emailValidate = MutableLiveData<Boolean>()
    val emailValidate = _emailValidate
    private val _passwordValidate = MutableLiveData<Boolean>()
    val passwordValidate = _passwordValidate

    private val _enterAccountResult = MutableLiveData<String>()
    val enterAccountResult = _enterAccountResult

    lateinit var email: String
    lateinit var password: String

    fun checkInput(emailValue: String, passwordValue: String,){

        validateEmail(emailValue)
        validatePassword(passwordValue)

        if (_emailValidate.value == true && _passwordValidate.value == true){
            Log.i(TAG,"ViewModelEnterAccount: происходит вход в аккаунт")

            launch(Dispatchers.IO){
                enterAccount()
            }

        }
    }

    private suspend fun enterAccount(){
        when (enterAccount.enterUser(email, password)) {
            "Выполнен вход!" -> _enterAccountResult.postValue("Выполнен вход!")
           // "ERROR_EMAIL_ALREADY_IN_USE" -> _createAccountResult.postValue("Email занят!")
            "Ошибка!" -> _enterAccountResult.postValue("Ошибка!")
        }
    }

    private fun validateEmail(emailValue: String){
        if (emailValue.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()){
            Log.i(TAG, "Enter account: Email Valid!")
            email = emailValue
            _emailValidate.value = true
        } else {
            Log.i(TAG, "Enter account: Email Ошибка!")
            _emailValidate.value = false
        }

    }

    private fun validatePassword(passwordValue: String){
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +
                    ".{8,16}" +
                    "$"
        )

        if (passwordValue.isEmpty()) {
            Log.i(TAG, "Enter account: Password is empty!$passwordValue")
            _passwordValidate.value = false

        } else if (!PASSWORD_PATTERN.matcher(passwordValue).matches()) {
            Log.i(TAG, "Enter account: Password too weak!")
            _passwordValidate.value = false
        } else {
            Log.i(TAG, "Enter account: Valid password!")
            password = passwordValue
            _passwordValidate.value = true
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}