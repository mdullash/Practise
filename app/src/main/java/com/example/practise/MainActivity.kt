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

    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var signinBtn : Button
    private lateinit var signUpBtn : Button
    private lateinit var auth : FirebaseAuth
    private lateinit var sharedPref : SharedPreferenceManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        sharedPref = SharedPreferenceManager(this)

        if(sharedPref.getLoginStatus())
        {
            startActivity(Intent(applicationContext,DashBoard::class.java))
            finish()
        }



        setContentView(R.layout.activity_main)



        auth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email_id)
        pass=findViewById(R.id.pass_id)
        signinBtn=findViewById(R.id.sign_btn)
        signUpBtn=findViewById(R.id.sign_up)


        signUpBtn.setOnClickListener {

            startActivity(Intent(this,SignUp::class.java))
            finish()
        }

        signinBtn.setOnClickListener(View.OnClickListener {



            if(email.length()==0)
            {
                email_id.error = "Enter email address"
                email_id.requestFocus()
                return@OnClickListener
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches())
            {
                email_id.error = "Enter an valid email address"
                email_id.requestFocus()
                return@OnClickListener
            }

            if (pass.length()==0)
            {
                pass_id.error = "Enter password"
                pass_id.requestFocus()
                return@OnClickListener
            }
            if(pass.length()<8)
            {
                pass_id.error = "Enter at least 8"
            }



            var firebaseModel=FirebaseModel()
            firebaseModel.userLogin(email.text.toString().trim(),pass.text.toString().trim())

            firebaseModel.currentName.observe(this, Observer {
                b -> Log.wtf("firebase", " "+b)
                if(b==true)
                {
                    sharedPref.setLoginStatus(true)
                    sharedPref.setEmail(email.text.toString().trim())
                    startActivity(Intent(this,DashBoard::class.java))
                    finish()

                }
                else if(b==false)
                {
                    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_LONG).show()
                }


            })

        })


    }

}
