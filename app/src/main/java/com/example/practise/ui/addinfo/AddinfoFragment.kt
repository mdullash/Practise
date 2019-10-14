package com.example.practise.ui.addinfo

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
import com.example.practise.R
import com.example.practise.helper.FirebaseInstance
import com.example.practise.helper.SharedPreferenceManager
import com.example.practise.model.User
import kotlinx.android.synthetic.main.fragment_addinfo.*

class AddinfoFragment : Fragment() {

    private lateinit var addinfoViewModel: AddinfoViewModel
    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var savebtn : Button
    private lateinit var sharedPref : SharedPreferenceManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addinfoViewModel =
            ViewModelProviders.of(this).get(AddinfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_addinfo, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        addinfoViewModel.text.observe(this, Observer {
            //textView.text = it
        })
        sharedPref = SharedPreferenceManager(context!!)

        name = root.findViewById(R.id.name_id)
        age = root.findViewById(R.id.age_id)
        savebtn = root.findViewById(R.id.save_btn)

        savebtn.setOnClickListener{

            saveData()
        }


        return root
    }

    private fun saveData() {

        var uname: String = name_id.text.toString().trim()
        var uage : String = age_id.text.toString().trim()
        val email : String? = sharedPref.getEmail()

        val id = FirebaseInstance.ref.push().key.toString()

        val user = User(uname, uage, email)

        FirebaseInstance.ref.child(id).setValue(user).addOnCompleteListener{

            Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()
        }
    }

}