package com.example.practise.ui.login

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel(){

    val validatepass: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val validatemail: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun validateEmail(email: String)
    {

        validatemail.value=android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }


    fun isvalidatePass(pass: String)
    {
        val PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[@_.]).*$")

        validatepass.value=!TextUtils.isEmpty(pass) && PASSWORD_PATTERN.matcher(pass).matches()
    }
}