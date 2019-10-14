package com.example.practise.ui.addinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddinfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is my home Fragment"
    }
    val text: LiveData<String> = _text
}