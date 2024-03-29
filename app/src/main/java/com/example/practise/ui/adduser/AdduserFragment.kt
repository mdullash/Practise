package com.example.practise.ui.adduser

import android.content.Intent
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
import com.example.practise.model.FirebaseModel
import com.example.practise.ui.login.LoginActivity
import com.example.practise.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*


class AdduserFragment : Fragment() {

    private lateinit var adduserViewModel: AdduserViewModel
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var signinBtn : Button
    private lateinit var signUpBtn : Button
    private lateinit var auth : FirebaseAuth
    private lateinit var firebaseModel : FirebaseModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adduserViewModel =
            ViewModelProviders.of(this).get(AdduserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_adduser, container, false)


        email= root.findViewById(R.id.email_id) as EditText
        pass=root.findViewById(R.id.pass_id) as EditText
        signUpBtn=root.findViewById(R.id.adduser_id) as Button

        signUpBtn.setOnClickListener {

            adduserViewModel.validateEmail(email.text.toString().trim())

            adduserViewModel.validatemail.observe(this, Observer {
                    b->
                        if(!b) {
                            email_id.error = "Email not in correct form"
                            email_id.requestFocus()
                        }

            })


            adduserViewModel.isvalidatePass(pass.text.toString().trim())

            adduserViewModel.currentName.observe(this, Observer {
                   b->
                    if(!b) {
                        pass_id.error = "Pass requirement not matched"
                        pass_id.requestFocus()
                    }


                })

            firebaseModel= FirebaseModel()
            firebaseModel.userReg(email.text.toString().trim(),pass.text.toString().trim())

            firebaseModel.currentReg.observe(this, Observer {

                task->
                    if(task)
                    {
                        startActivity(Intent(activity, LoginActivity::class.java))
                    }

                    else
                    {
                        Toast.makeText(activity,"Sign Up failed",Toast.LENGTH_LONG).show()
                    }
            })
        }

        return root
    }
}