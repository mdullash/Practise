package com.example.practise.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.practise.ui.login.LoginActivity
import com.example.practise.R
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var signintxt : TextView
    private lateinit var signUpBtn : Button
    private lateinit var signupProgress : ProgressBar
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email_id)
        pass=findViewById(R.id.pass_id)
        signintxt=findViewById(R.id.signin_txtv)
        signUpBtn=findViewById(R.id.sign_up)
        signupProgress=findViewById(R.id.signup_progressbar)

        signupProgress.visibility= View.GONE

        signUpBtn.setOnClickListener {

            signupProgress.visibility= View.VISIBLE
            userReg()

        }

        signintxt.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun userReg() {

        var uemail : String = email.text.toString()
        var upass : String = pass.text.toString()

        auth.createUserWithEmailAndPassword(uemail,upass).addOnCompleteListener{

            task ->
            if(task.isSuccessful)
            {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else
            {
                Toast.makeText(applicationContext,"Sign Up failed",Toast.LENGTH_LONG).show()
            }

        }

        
    }
}
