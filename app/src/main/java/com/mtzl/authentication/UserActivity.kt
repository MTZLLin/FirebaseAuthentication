package com.mtzl.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    lateinit var database : FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        database = Firebase.database

        auth = Firebase.auth

        //Depend on database hierarchical level
        dbRef = database.getReference("Users").child(auth.currentUser!!.uid).child(auth.currentUser!!.uid)

        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                //val value = snapshot.getValue(User::class.java)
                var name = "${snapshot.child("firstName").value} ${snapshot.child("lastName").value}"

                //1. val user = snapshot.getValue(User::class.java)
                /* if (user == null){
                     txtUser.text = "user is empty"
                 }else{
                     Log.e("User",user.toString())

                     txtUser.text = user?.firstName
                     //1.txtUser.text = value.toString() //Show as array values
                 }*/
                txtUser.text = name.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}