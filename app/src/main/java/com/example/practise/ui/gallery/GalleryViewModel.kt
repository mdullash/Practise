package com.example.practise.ui.gallery

import android.text.TextUtils
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.regex.Pattern

class GalleryViewModel : ViewModel() {

    val currentName: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val validateemail: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun validateEmail(email: String)
    {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                validateemail.value=false
            }
        else
        {
            validateemail.value=false
        }
    }


    fun isvalidatePass(pass: String)
    {
        val PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[@_.]).*$")

        if (!TextUtils.isEmpty(pass) && PASSWORD_PATTERN.matcher(pass).matches())
        {
            currentName.value=true
        }
        else
        {
            currentName.value=false
        }
    }
}