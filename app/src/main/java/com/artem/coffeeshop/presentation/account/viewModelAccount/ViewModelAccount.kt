package com.artem.coffeeshop.presentation.account.viewModelAccount


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.coffeeshop.TAG
import com.artem.coffeeshop.domain.CreateAccountUseCase

import java.util.regex.Pattern

class ViewModelAccount(private val createAccount:CreateAccountUseCase) : ViewModel() {

    private  val _emailValidate = MutableLiveData<Boolean>()
    val emailValidate = _emailValidate

    private val _passwordValidate = MutableLiveData<Boolean>()
    val passwordValidate = _passwordValidate


    private val _firstName = MutableLiveData<Boolean>()
    val firstName = _firstName

    private val _lastName = MutableLiveData<Boolean>()
    val lastName = _lastName




     fun checkInput(emailValue:String,passwordValue:String,firstNameValue:String,lastNameValue:String) {

         validateEmail(emailValue)
         validatePassword(passwordValue)
         validateFirstName(firstNameValue)
         validateLastName(lastNameValue)

         if (_emailValidate.value == true && _passwordValidate.value == true && _firstName.value == true && _lastName.value == true) {

             Log.i(TAG,"All Data is correct!")

             //Создание аккаунта в Firebase
             //Проверка на занятый Email если все нормально
             //Переход к входу в аккаунт
         }
    }


    private fun validateEmail(emailValue:String){
        if (emailValue.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()){
            Log.i(TAG,"Valid Email!")
            _emailValidate.value = true
        } else{
            Log.i(TAG,"Invalid Email!")
            _emailValidate.value = false
        }
    }

    private fun validatePassword(passwordValue:String){
        val PASSWORD_PATTERN:Pattern = Pattern.compile("^" +
                "(?=.*[a-zA-Z])" +
                "(?=\\S+$)" +
                ".{8,16}" +
                "$")

        if (passwordValue.isEmpty() ){
            Log.i(TAG, "Password is empty!$passwordValue")
            _passwordValidate.value = false

        } else if (!PASSWORD_PATTERN.matcher(passwordValue).matches()){
            Log.i(TAG, "Password too weak!" )
            _passwordValidate.value = false
        }

        else{
            Log.i(TAG,"Valid password!" )
            _passwordValidate.value = true
        }

    }



    private fun validateFirstName(firstNameValue: String){

        val FIRST_NAME_PATTERN:Pattern = Pattern.compile("^" +
                "(?=.*[а-яА-Я])" +
                "(?=\\S+$)" +
                ".{2,20}"+
                "$")

        if (firstNameValue.isEmpty()){
            Log.i(TAG, "First name is empty!$firstNameValue")
            _firstName.value = false

        } else if (!FIRST_NAME_PATTERN.matcher(firstNameValue).matches()){
            Log.i(TAG,"first name not Pattern!")
            _firstName.value = false
        }

        else{
            Log.i(TAG,"first name is correct!")
            _firstName.value = true
        }
    }

    private fun validateLastName(lastNameValue: String){
        val LAST_NAME_PATTERN:Pattern = Pattern.compile("^" +
                "(?=.*[а-яА-Я])" +
                "(?=\\S+$)" +
                ".{2,20}"+
                "$")

        if (lastNameValue.isEmpty()){
            Log.i(TAG, "Last name is empty!$lastNameValue")
            _lastName.value = false

        } else if (!LAST_NAME_PATTERN.matcher(lastNameValue).matches()){
            Log.i(TAG,"Last name not Pattern!")
            _lastName.value = false
        }

        else{
            Log.i(TAG,"Last name is correct!")
            _lastName.value = true
        }
    }


}