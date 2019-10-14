package com.example.practise.ui.showinfo

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
import com.example.practise.helper.FirebaseInstance
import com.example.practise.R
import com.example.practise.model.Data
import com.example.practise.model.Header
import com.example.practise.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_sign_up.*

class ShowinfoFragment : Fragment() {

    private lateinit var showinfoViewModel: ShowinfoViewModel
    private lateinit var recyclerView: RecyclerView
    private var users : ArrayList<Data> = ArrayList()
    private lateinit var adapter : MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_showinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showinfoViewModel = ViewModelProviders.of(this).get(ShowinfoViewModel::class.java)

        showinfoViewModel = ShowinfoViewModel()
        recyclerView=view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)

        showinfoViewModel.data.observe(this, Observer {
                data->
                 users.addAll(data)
                 adapter.update(users)

        })

        adapter=MyAdapter(users)
        showinfoViewModel.fetchData()
        recyclerView.adapter = adapter


    }

}