package com.artem.coffeeshop.presentation.account.viewModelAccount


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.coffeeshop.TAG

import java.util.regex.Pattern

class ViewModelAccount : ViewModel() {

    private  val _emailValidate = MutableLiveData<Boolean>()
    val emailValidate = _emailValidate

    private val _passwordValidate = MutableLiveData<Boolean>()
    val passwordValidate = _passwordValidate


/*
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _firsName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firsName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName
*/


     fun checkInput(emailValue:String,passwordValue:String,phoneNumberValue:String,firstNameValue:String,lastNameValue:String) {

         validateEmail(emailValue)
         validatePassword(passwordValue)
         validatePhoneNumber(phoneNumberValue)
         validateFirstName(firstNameValue)
         validateLastName(lastNameValue)



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

    private fun validatePhoneNumber(phoneNumberValue: String){}

    private fun validateFirstName(firstNameValue: String){}

    private fun validateLastName(lastNameValue: String){}


}