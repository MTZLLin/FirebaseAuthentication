package com.mtzl.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnSignup
import kotlinx.android.synthetic.main.activity_main.edtEmail
import kotlinx.android.synthetic.main.activity_main.edtPassword
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initFirebase()
    }

    private fun initFirebase(){
        auth = Firebase.auth

        btnSignupActivity.setOnClickListener {
            var email: String = edtEmail.text.toString()
            var password: String = edtPassword.text.toString()

            val  firstname: String =edtFirstName.text.toString()
            val lastname: String = edtLastName.text.toString()
            val phonenumber: String = edtPhoneNumber.text.toString()
            createAccount(email, password,firstname,lastname,phonenumber)
        }

    }

    private fun createAccount(email: String, password: String,firstname:String,lastname: String,phoneNumber: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Success signup", Toast.LENGTH_LONG).show()

                    val userId = auth.currentUser?.uid
                    val database = Firebase.database

                    //val ref = database.reference.child("Users")
                    // ref.setValue(userId)
                    val ref = database.getReference("Users")
                    val userDB = ref.child(userId!!)

                    var user = User(firstname,lastname,phoneNumber,userId)
                    userDB.child(userId).setValue(user)

                    /*userDB.child("first name").setValue(firstname)
                    userDB.child("last name").setValue(lastname)
                    userDB.child("phone number").setValue(phonenumber)
                    */
                    finish()

                }else{
                    Toast.makeText(this,"Fail SignUp" + task.exception, Toast.LENGTH_LONG).show()
                    Log.d("Error>>>>", task.exception.toString())
                }
            }
    }

}