package com.example.mentalhealth.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.mentalhealth.Uploads
import com.example.mentalhealth.audio.MusicActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import mentalhealth.R

class Resources: Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.resources, container, false)
        var welcom =  view.findViewById<TextView>(R.id.welcomemessage)

//        var welcom = view.findViewById<TextView>(R.id.welcomemessage)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDbRef = database?.reference!!.child("users")
        val user = mAuth.currentUser

        var userreference = mDbRef.child(user?.uid!!)
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                welcom.text =  "Welcome " + snapshot.child("name").value.toString() + " select one based on how feeling today "

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

       val  upload = view.findViewById<Button>(R.id.upload)

        upload.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), Uploads::class.java)
            startActivity(intent)
        })



        val happy = view.findViewById<CardView>(R.id.audiomusicH)
        val sad = view.findViewById<CardView>(R.id.cardviewMeditation)
        val  sleping = view.findViewById<CardView>(R.id.cardviewmusicS)
        val audiomusicAnx = view.findViewById<CardView>(R.id.audiomusicAnx)

        sad.setOnClickListener {
            val intent = Intent(requireContext(), com.example.mentalhealth.sad.MusicActivity::class.java)
            startActivity(intent)
        }

        happy.setOnClickListener {
            val intent = Intent(requireContext(), com.example.mentalhealth.anxiety.MusicActivity::class.java)
            startActivity(intent)
        }


        sleping.setOnClickListener {
            val intent = Intent(requireContext(), com.example.mentalhealth.Sleeping.MusicActivity::class.java)
            startActivity(intent)
        }

//        photomeme.setOnClickListener {
//            val intent = Intent(requireContext(), ViewMemes::class.java)
//            startActivity(intent)
//        }
//
//        memevideo.setOnClickListener {
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
//        }

        audiomusicAnx.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)
        }

        return view
    }




}