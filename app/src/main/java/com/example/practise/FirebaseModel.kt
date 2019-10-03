package com.example.practise

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class FirebaseModel
{
    //var isloginSuccessful=false
    val currentName: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val currentReg: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    //val obj=MainActivity()
    fun userLogin(email : String,pass : String) {

        var auth : FirebaseAuth

        auth= FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(OnCompleteListener {
                task ->
            currentName.value=task.isSuccessful

        })

    }

    fun userReg(emial : String, pass : String)
    {
        var auth : FirebaseAuth

        auth= FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(emial,pass).addOnCompleteListener(OnCompleteListener {

            task ->
            if(task.isSuccessful)
            {
                currentReg.value=task.isSuccessful
            }


        })
    }
}