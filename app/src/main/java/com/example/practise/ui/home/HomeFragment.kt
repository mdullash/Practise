package com.example.practise.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.practise.FirebaseInstance
import com.example.practise.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var name : EditText
    lateinit var age : EditText
    lateinit var savebtn : Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            //textView.text = it
        })



        name = root.findViewById(R.id.name_id)
        age = root.findViewById(R.id.age_id)
        savebtn = root.findViewById(R.id.save_btn)

        savebtn.setOnClickListener{

            saveData()
        }


        return root
    }

    private fun saveData() {

        var u_name: String = name_id.text.toString().trim()
        var u_age : String = age_id.text.toString().trim()


        val id = FirebaseInstance.ref.push().key.toString()

        val user = User(u_name,u_age)

        FirebaseInstance.ref.child(id).setValue(user).addOnCompleteListener{

            Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()
        }
    }



}