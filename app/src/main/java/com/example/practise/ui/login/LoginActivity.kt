package com.example.practise.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.practise.R
import com.example.practise.helper.SharedPreferenceManager
import com.example.practise.model.FirebaseModel
import com.example.practise.ui.dashboard.DashBoard
import com.example.practise.ui.signup.SignUp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var signinBtn : Button
    private lateinit var signUpBtn : Button
    private lateinit var auth : FirebaseAuth
    private lateinit var sharedPref : SharedPreferenceManager
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var firebaseModel: FirebaseModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = SharedPreferenceManager(this)

        if(sharedPref.getLoginStatus())
        {
            startActivity(Intent(applicationContext, DashBoard::class.java))
            finish()
        }

        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        auth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email_id)
        pass=findViewById(R.id.pass_id)
        signinBtn=findViewById(R.id.sign_btn)
        signUpBtn=findViewById(R.id.sign_up)


        signUpBtn.setOnClickListener {

            startActivity(Intent(this, SignUp::class.java))
            finish()
        }

        signinBtn.setOnClickListener(View.OnClickListener {



            loginViewModel.validateEmail(email.text.toString().trim())

            loginViewModel.validatemail.observe(this, Observer {
                    b->
                        if(!b) {
                            email_id.error = "Email not in correct form"
                            email_id.requestFocus()
                        }

            })


            loginViewModel.isvalidatePass(pass.text.toString().trim())

            loginViewModel.validatepass.observe(this, Observer {
                    b->
                        if(!b) {
                            pass_id.error = "Pass requirement not matched"
                            pass_id.requestFocus()
                        }


            })


            firebaseModel= FirebaseModel()
            firebaseModel.userLogin(email.text.toString().trim(),pass.text.toString().trim())

            firebaseModel.currentName.observe(this, Observer {
                b ->
                if(b==true)
                {
                    sharedPref.setLoginStatus(true)
                    sharedPref.setEmail(email.text.toString().trim())
                    startActivity(Intent(this, DashBoard::class.java))
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
