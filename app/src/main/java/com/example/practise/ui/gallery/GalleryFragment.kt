package com.example.practise.ui.gallery

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
import com.example.practise.DashBoard
import com.example.practise.FirebaseModel
import com.example.practise.MainActivity
import com.example.practise.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*


class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    lateinit var email : EditText
    lateinit var pass : EditText
    lateinit var signinBtn : Button
    lateinit var signUpBtn : Button
    lateinit var auth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_adduser, container, false)

        //DashBoard.count+=1

        email= root.findViewById(R.id.email_id) as EditText
        pass=root.findViewById(R.id.pass_id) as EditText
        signUpBtn=root.findViewById(R.id.adduser_id) as Button

        signUpBtn.setOnClickListener(View.OnClickListener {


            var gvm=GalleryViewModel()

            gvm.validateEmail(email.text.toString().trim())

            gvm.validateemail.observe(this, Observer {
                    b->
                if(!b) {
                    email_id.setError("Email not in correct form")
                    email_id.requestFocus()
                }


            })


            gvm.isvalidatePass(pass.text.toString().trim())

            gvm.currentName.observe(this, Observer {
                   b->
                if(!b) {
                    pass_id.setError("Pass requirement not matched")
                    pass_id.requestFocus()
                }


                })

            var firebaseModel=FirebaseModel()
            firebaseModel.userReg(email.text.toString().trim(),pass.text.toString().trim())

            firebaseModel.currentReg.observe(this, Observer {

                task->
                if(task)
                {
                    startActivity(Intent(activity,MainActivity::class.java))
                }

                else
                {
                    Toast.makeText(activity,"Sign Up failed",Toast.LENGTH_LONG).show()
                }
            })
        })

        return root
    }
}