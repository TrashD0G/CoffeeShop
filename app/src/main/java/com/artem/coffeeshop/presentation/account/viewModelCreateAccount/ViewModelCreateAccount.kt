package com.artem.coffeeshop.presentation.account.viewModelCreateAccount


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.coffeeshop.domain.CreateAccountUseCase
import com.artem.coffeeshop.presentation.mainScreen.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class ViewModelCreateAccount(private val createAccount: CreateAccountUseCase) : ViewModel(),
    CoroutineScope {

    private val _emailValidate = MutableLiveData<Boolean>()
    val emailValidate = _emailValidate
    private val _passwordValidate = MutableLiveData<Boolean>()
    val passwordValidate = _passwordValidate
    private val _firstName = MutableLiveData<Boolean>()
    val firstName = _firstName


    private val _createAccountResult = MutableLiveData<String>()
    val createAccountResult = _createAccountResult


    lateinit var email: String
    lateinit var password: String


    fun checkInput(
        emailValue: String,
        passwordValue: String,
        firstNameValue: String,
    ) {

        validateEmail(emailValue)
        validatePassword(passwordValue)
        validateFirstName(firstNameValue)

        if (_emailValidate.value == true && _passwordValidate.value == true && _firstName.value == true) {

            Log.i(TAG, "Create account: All Data is correct!")

            launch(Dispatchers.IO) {
                createAccount()
            }

        }
    }

    private suspend fun createAccount() {
        when (createAccount.createUser(email, password)) {
            "Аккаунт создан!" -> _createAccountResult.postValue("Аккаунт создан!")
            "ERROR_EMAIL_ALREADY_IN_USE" -> _createAccountResult.postValue("Email занят!")
            "Ошибка!" -> _createAccountResult.postValue("Ошибка!")
        }
    }

    private fun validateEmail(emailValue: String) {
        if (emailValue.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            Log.i(TAG, "Create account: Valid Email!")
            email = emailValue
            _emailValidate.value = true
        } else {
            Log.i(TAG, "Create account: Invalid Email!")
            _emailValidate.value = false
        }
    }

    private fun validatePassword(passwordValue: String) {
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +
                    ".{8,16}" +
                    "$"
        )

        if (passwordValue.isEmpty()) {
            Log.i(TAG, "Create account: Password is empty!$passwordValue")
            _passwordValidate.value = false

        } else if (!PASSWORD_PATTERN.matcher(passwordValue).matches()) {
            Log.i(TAG, "Create account: Password too weak!")
            _passwordValidate.value = false
        } else {
            Log.i(TAG, "Create account: Valid password!")
            password = passwordValue
            _passwordValidate.value = true
        }

    }

    private fun validateFirstName(firstNameValue: String) {

        val FIRST_NAME_PATTERN: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[а-яА-Я])" +
                    "(?=\\S+$)" +
                    ".{2,20}" +
                    "$"
        )

        if (firstNameValue.isEmpty()) {
            Log.i(TAG, "Create account: First name is empty!$firstNameValue")
            _firstName.value = false

        } else if (!FIRST_NAME_PATTERN.matcher(firstNameValue).matches()) {
            Log.i(TAG, "Create account: first name not Pattern!")
            _firstName.value = false
        } else {
            Log.i(TAG, "Create account: first name is correct!")
            _firstName.value = true
        }
    }



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}