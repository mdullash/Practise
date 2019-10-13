package com.example.practise.ui.send

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practise.R
import com.example.practise.ui.home.Data
import com.example.practise.ui.home.Header
import com.example.practise.ui.home.User

class MyAdapter(private var users : ArrayList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var TYPE_HEADER = 0
    var TYPE_ITEM = 1

    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
    {
        var fname : TextView = itemview.findViewById((R.id.name))
        var fage : TextView = itemview.findViewById((R.id.age))
    }

    class HeaderViewholder(itemview : View) : RecyclerView.ViewHolder(itemview)
    {
        var addedBy : TextView = itemview.findViewById(R.id.recycle_header)
    }


    fun update(users : ArrayList<Data>)
    {
        this.users = users
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {

        return if(viewType == TYPE_HEADER) {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_header,parent,false)

            HeaderViewholder(view)
        } else {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_info,parent,false)

            ViewHolder(view)

        }
    }

    override fun getItemCount(): Int {
        //Log.wtf("onCreateViewHolder ", " users.size "+users.size)

        return users.size
    }

    override fun getItemViewType(position: Int): Int {

        return if(users[position] is User)
        {
            TYPE_ITEM
        }
        else
        {
            TYPE_HEADER
        }
//        return if(position==0) {
//            TYPE_HEADER
//        } else {
//            TYPE_ITEM
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewHolder)
        {
            val name = "Name: ${(users[position] as User).name}"
            val age = "Age: ${(users[position] as User).age}"

            holder.fname.text = name
            holder.fage.text = age
        }
        else if(holder is HeaderViewholder)
        {

            val addedBy = "${(users[position] as Header).addedBy}"

                holder.addedBy.text = addedBy
        }


    }

}