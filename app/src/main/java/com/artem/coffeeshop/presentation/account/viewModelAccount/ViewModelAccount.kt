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

    private val _phoneNumberValidate = MutableLiveData<Boolean>()
    val phoneNumberValidate = _phoneNumberValidate

    private val _firstName = MutableLiveData<Boolean>()
    val firstName = _firstName

    private val _lastName = MutableLiveData<Boolean>()
    val lastName = _lastName


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

    private fun validatePhoneNumber(phoneNumberValue: String){

        if (phoneNumberValue.isEmpty()){
            Log.i(TAG, "Phone number is empty!$phoneNumberValue")
            _phoneNumberValidate.value = false

        } else if (!Patterns.PHONE.matcher(phoneNumberValue).matches()){
            Log.i(TAG,"Phone number not Pattern!")
            _phoneNumberValidate.value = false
        }

        else{
            Log.i(TAG,"Phone number is correct!")
            _phoneNumberValidate.value = true
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