package com.example.practise

import android.content.Context
import android.content.SharedPreferences


class SharedPreferenceManager(context: Context)
{
    var pref : SharedPreferences = context.getSharedPreferences("login",Context.MODE_PRIVATE)

    fun setStatus (status : Boolean)
    {
        val editor = pref.edit()
        editor.putBoolean("stat",status)
        editor.apply()
    }

    fun getStatus () : Boolean
    {
        val status = pref.getBoolean("stat",false)
        return status
    }

}