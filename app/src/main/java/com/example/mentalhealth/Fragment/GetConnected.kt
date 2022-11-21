package com.example.mentalhealth.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import mentalhealth.R

class GetConnected : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_get_connected, container, false)
        val facebook = view.findViewById<CardView>(R.id.cardviewFacebook)
        val whatsapp = view.findViewById<CardView>(R.id.cardviewWhatsapp)
        val twitter = view.findViewById<CardView>(R.id.cardviewTwitter)
        val linkedin = view.findViewById<CardView>(R.id.cardviewLinkedIn)

            var welcomemessage = view.findViewById<TextView>(R.id.welcomemessage)

            mAuth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            mDbRef = database?.reference!!.child("users")
            val user = mAuth.currentUser

            var userreference = mDbRef.child(user?.uid!!)
            userreference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    welcomemessage.text =  "Welcome " + snapshot.child("name").value.toString() + "to Get connected Module"

                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        facebook.setOnClickListener {
            val url = "https://www.facebook.com/groups/mhawarenessandsupport/?ref=share"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        twitter.setOnClickListener {
            val url = "https://twitter.com/stay__strong___?s=21&t=RBdiPtAH7awGCzOVmj2jEw"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        whatsapp.setOnClickListener {
            val url = "http://www.whatsapp.com;/0718606698"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        linkedin.setOnClickListener {
            val url = "http://www.linkedin.com/mental-health101"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        return view
    }
}
