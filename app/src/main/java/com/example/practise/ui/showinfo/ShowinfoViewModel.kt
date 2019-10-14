package com.example.practise.ui.showinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practise.helper.FirebaseInstance
import com.example.practise.model.Data
import com.example.practise.model.Header
import com.example.practise.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ShowinfoViewModel : ViewModel() {

    val data: MutableLiveData<ArrayList<Data>> by lazy {
        MutableLiveData<ArrayList<Data>>()
    }
    private var datas : ArrayList<Data> = ArrayList()
    private var emails : ArrayList<String> = ArrayList()

    fun fetchData() {

        FirebaseInstance.ref.orderByChild("addedBy").addValueEventListener(valueEventListener)

    }

    private var valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            //Log.i("onCreateViewHolder ", "dataSnapshot: $dataSnapshot")
            dataSnapshot.children.forEach { data ->

                val user: Data? = data.getValue(Header::class.java)

                if(emails.isEmpty()) {
                      datas.add(user!!)
                      emails.add((user as Header).addedBy!!)

                  }

                else {

                      if (!emails.contains((user as Header).addedBy)) {
                              datas.add(user)
                              emails.add(user.addedBy!!)
                      }

                  }

                val userdata: Data? = data.getValue(User::class.java)
                datas.add(userdata!!)

            }
            data.value = datas


        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }
}