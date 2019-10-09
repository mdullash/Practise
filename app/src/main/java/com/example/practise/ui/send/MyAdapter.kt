package com.example.practise.ui.send

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practise.R
import com.example.practise.ui.home.User

class MyAdapter(private var users : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.ViewHolder>()
{
    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
    {
        var fname : TextView = itemview.findViewById((R.id.name))
        var fage : TextView = itemview.findViewById((R.id.age))
    }

    fun update(users : ArrayList<User>)
    {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_info,parent,false)
        Log.wtf("onCreateViewHolder ", " edfasdfa")
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.wtf("onCreateViewHolder ", " users.size "+users.size)

        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = "Name: ${users[position].name}"
        val age = "Age: ${users[position].age}"
        holder.fname.text = name
        holder.fage.text = age

    }

}