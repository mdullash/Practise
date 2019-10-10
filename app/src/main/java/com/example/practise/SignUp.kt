package com.example.practise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var signinBtn : Button
    private lateinit var signUpBtn : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email_id)
        pass=findViewById(R.id.pass_id)
        signinBtn=findViewById(R.id.sign_btn)
        signUpBtn=findViewById(R.id.sign_up)

        signUpBtn.setOnClickListener {

            userReg()


        }

        signinBtn.setOnClickListener {

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun userReg() {

        var u_email : String = email.text.toString()
        var u_pass : String = pass.text.toString()

        auth.createUserWithEmailAndPassword(u_email,u_pass).addOnCompleteListener{

            task ->
            if(task.isSuccessful)
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else
            {
                Toast.makeText(applicationContext,"Sign Up failed",Toast.LENGTH_LONG).show()
            }

        }

        
    }
}
