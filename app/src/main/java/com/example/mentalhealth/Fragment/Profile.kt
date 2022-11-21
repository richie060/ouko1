package com.example.mentalhealth.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mentalhealth.activities.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.profile.*
import mentalhealth.R

class Profile: Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile, container, false)
        return view
    }
    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var firstname = view.findViewById<TextView>(R.id.firstname)
        var email = view.findViewById<TextView>(R.id.email)
        var logout = view.findViewById<Button>(R.id.btnlogout)

        logout.setOnClickListener {
            mAuth.signOut()
            val intent= Intent(requireContext(), Login::class.java)
            startActivity(intent)
        }

//
//        loadProfile()

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDbRef = database?.reference!!.child("user")
        val user = mAuth.currentUser

        email.text = user?.email
        username.text = user?.email

        var userreference = mDbRef.child(user?.uid!!)
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                firstname.text = snapshot.child("name").value.toString()
                fullname.text = snapshot.child("name").value.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

//    private fun loadProfile(){
//        val user = mAuth.currentUser
//        val userreference = mDbRef.child(user?.uid!!)
//        email.text = user?.email
//        userreference.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                firstname.text = snapshot.child("name").value.toString()
//                fullname.text = snapshot.child("lastname").value.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
}

