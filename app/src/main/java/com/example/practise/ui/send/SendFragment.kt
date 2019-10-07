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
import com.example.practise.ui.home.User
import com.google.firebase.database.*

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var ref: DatabaseReference
    private var users : ArrayList<User> = ArrayList()
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

        recyclerView=view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        adapter=MyAdapter(users)
        recyclerView.adapter = adapter

        fetchData()
    }

    fun fetchData()
    {
        FirebaseInstance.ref.addValueEventListener(valueEventListener)
    }

    private var valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.i("onCreateViewHolder ", "dataSnapshot: $dataSnapshot")
            dataSnapshot.children.forEach { data ->
                val user: User? = data.getValue(User::class.java)
                users.add(user!!)
            }

            adapter.update(users)
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }



}