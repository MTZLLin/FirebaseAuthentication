package com.mtzl.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnSignup
import kotlinx.android.synthetic.main.activity_main.edtEmail
import kotlinx.android.synthetic.main.activity_main.edtPassword
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
    }
    private fun initFirebase(){
        auth = Firebase.auth

        btnSignup.setOnClickListener {
            //var email: String = edtEmail.text.toString()
            //var password: String = edtPassword.text.toString()
            //createAccount(email, password)

            var intent = Intent(this, SignupActivity::class.java )
            startActivity(intent)

        }
        btnSignin.setOnClickListener {
            var email: String = edtEmail.text.toString()
            var password: String = edtPassword.text.toString()

            signin(email, password)

        }

    }

   /* private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Success signup", Toast.LENGTH_LONG).show()

                    val userID = auth.currentUser?.uid
                }else{
                    Toast.makeText(this,"Fail Signup", Toast.LENGTH_LONG).show()
                }
            }
    }*/
    private fun signin(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Signin Successful"+ auth.currentUser?.uid,Toast.LENGTH_LONG).show()
                    //val userId = auth.currentUser?.uid

                    var intent = Intent(this, UserActivity::class.java )
                    startActivity(intent)

                }else{
                    Toast.makeText(this,"Signin Fail"+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

}