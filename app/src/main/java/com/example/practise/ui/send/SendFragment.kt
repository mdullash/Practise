package com.example.practise.ui.send

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practise.FirebaseInstance
import com.example.practise.R
import com.example.practise.ui.home.Data
import com.example.practise.ui.home.Header
import com.example.practise.ui.home.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel
    private lateinit var recyclerView: RecyclerView
    private var users : ArrayList<Data> = ArrayList()
    private var emails : ArrayList<String> = ArrayList()
    private lateinit var adapter : MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)
        //val textView: TextView = root.findViewById(R.id.text_send)
        sendViewModel.text.observe(this, Observer {
            //textView.text = it
        })

        //DashBoard.count+=1

        recyclerView=view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        adapter=MyAdapter(users)
        recyclerView.adapter = adapter

        fetchData()
    }

    fun fetchData()
    {
        FirebaseInstance.ref.orderByChild("addedBy").addValueEventListener(valueEventListener)

//        users.add(Header("abc@gmail.com"))
//        users.add(User("a","22","abc@gmail.com"))
//        users.add(User("b","23","abc@gmail.com"))
//        users.add(User("e","26","abc@gmail.com"))
//        users.add(Header("abcde@gmail.com"))
//        users.add(User("c","24","abcde@gmail.com"))
//        users.add(User("d","25","abcde@gmail.com"))
//
//        adapter.update(users)
    }

    private var valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            //Log.i("onCreateViewHolder ", "dataSnapshot: $dataSnapshot")
            dataSnapshot.children.forEach { data ->

                   val user: Data? = data.getValue(Header::class.java)
                //(user as Header).addedBy
                  if(emails.isEmpty()) {
                      users.add(user!!)
                      emails.add((user as Header).addedBy!!)
                  }

                else {
                      //emails.forEach { email ->

                          if (!emails.contains((user as Header).addedBy)) {
                              users.add(user)
                              emails.add(user.addedBy!!)
                          }
                      else
                          {
                              val user2: Data? = data.getValue(User::class.java)
                              users.add(user2!!)
                          }

                      //}
                  }




                }

            adapter.update(users)
            Log.wtf("users","rjh :$users")

        }


        override fun onCancelled(databaseError: DatabaseError) {

        }
    }



}