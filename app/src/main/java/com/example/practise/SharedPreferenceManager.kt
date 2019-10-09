package com.example.practise

import android.content.Context
import android.content.SharedPreferences


class SharedPreferenceManager(context: Context)
{
    var pref : SharedPreferences = context.getSharedPreferences("com.example.practise",Context.MODE_PRIVATE)

    fun setLoginStatus (status : Boolean)
    {
        val editor = pref.edit()
        editor.putBoolean("stat",status)
        editor.apply()
    }

    fun getLoginStatus () : Boolean
    {
        val status = pref.getBoolean("stat",false)
        return status
    }

    fun setEmail (emailStatus : String)
    {
        val editor = pref.edit()
        editor.putString("email",emailStatus)
        editor.apply()
    }

    fun getEmail () : String?
    {
        val status : String? = pref.getString("email","")
        return status
    }

}