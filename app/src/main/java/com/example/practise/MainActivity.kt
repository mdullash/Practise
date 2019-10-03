package com.example.practise

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern
import kotlinx.android.synthetic.main.activity_dash_board.drawer_layout as drawer_layout1

class MainActivity : AppCompatActivity() {

    lateinit var email : EditText
    lateinit var pass : EditText
    lateinit var signinBtn : Button
    lateinit var signUpBtn : Button
    //lateinit var layout : DrawerLayout
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email_id) as EditText
        pass=findViewById(R.id.pass_id) as EditText
        signinBtn=findViewById(R.id.sign_btn) as Button
        signUpBtn=findViewById(R.id.sign_up) as Button
        //layout=findViewById(R.id.drawer_layout)


        signUpBtn.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this,SignUp::class.java))
        })

        signinBtn.setOnClickListener(View.OnClickListener {



            if(email.length()==0)
            {
                email_id.setError("Enter email address")
                email_id.requestFocus()
                return@OnClickListener
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches())
            {
                email_id.setError("Enter an valid email address")
                email_id.requestFocus()
                return@OnClickListener
            }

            if (pass.length()==0)
            {
                pass_id.setError("Enter password")
                pass_id.requestFocus()
                return@OnClickListener
            }
            if(pass.length()>0 && pass.length()<8)
            {
                pass_id.setError("Enter at least 8")
            }

            var firebaseModel=FirebaseModel()
            firebaseModel.userLogin(email.text.toString().trim(),pass.text.toString().trim())

            firebaseModel.currentName.observe(this, Observer {
                b -> Log.wtf("firebase", " "+b)
                if(b==true)
                {
                    startActivity(Intent(this,DashBoard::class.java))

                }
                else if(b==false)
                {
                    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_LONG).show()
                }


            })

        })


    }

}
