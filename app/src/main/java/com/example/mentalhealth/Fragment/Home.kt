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
import androidx.fragment.app.FragmentTransaction
import com.example.mentalhealth.FAQFragment
import com.example.mentalhealth.Games
import com.example.mentalhealth.MeditationActivity
import com.example.mentalhealth.audio.MusicActivity
import com.example.mentalhealth.otp.MainActivity
import com.example.mentalhealth.positivenotes.StroryList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import mentalhealth.R

class Home : Fragment(R.layout.home) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase


    val number = "911"
    val REQUEST_PHONE_CALL = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home, container, false)
//        val call = view.findViewById<Button>(R.id.btncall)

        var welcomemessage = view.findViewById<TextView>(R.id.welcomemessagem)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDbRef = database?.reference!!.child("users")
        val use = mAuth.currentUser

        var userreference = mDbRef.child(use?.uid!!)
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                welcomemessage.text =  "Welcome " + snapshot.child("name").value.toString() + " to  Nature'S Therapy"

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        var usertyp = view.findViewById<TextView>(R.id.usertype)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        mDbRef = database?.reference!!.child("users").child(
            FirebaseAuth.getInstance().getCurrentUser()!!.getUid())
        val user = mAuth.currentUser

        var usert = mDbRef.child(user?.uid!!)

        usert.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var type = snapshot.child("type").value.toString()
                if (type.equals("Patients/Doctors")){
                    usertyp.setText("Users")
                }else{
                    usertyp.setText("Users")
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })



        val cardViewResources = view.findViewById<CardView>(R.id.cardviewResources)

        val cardviewMeditation = view.findViewById<CardView>(R.id.cardviewMeditation)
        val cardviewProfile = view.findViewById<CardView>(R.id.cardviewProfile)
        val cardviewCounsellors = view.findViewById<CardView>(R.id.cardviewCounselors)
        val cardViewGames= view.findViewById<CardView>(R.id.cardviewGames)
        val Success_stories = view.findViewById<CardView>(R.id.cardviewsuccessStories)

        cardViewResources.setOnClickListener {
            val intent = Intent(requireContext(),StroryList::class.java)
            startActivity(intent)
        }
        cardViewGames.setOnClickListener {

                        val games =   GetConnected()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout, games)
            transaction.commit()
        }

        cardviewMeditation.setOnClickListener {
            val intent = Intent(requireContext(), com.example.mentalhealth.player.MainActivity::class.java)
            startActivity(intent)
//            val meditation =   Meditation2()
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.frlayout, meditation)
//            transaction.commit()
        }

        cardviewProfile.setOnClickListener {
            val resources =   Resources()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout, resources)
            transaction.commit()
        }


        cardviewCounsellors.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }



//        cardViewconnect.setOnClickListener {
//            val connect =   GetConnected()
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.frlayout, connect)
//            transaction.commit()
//        }

        Success_stories.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)


        }

//        call.setOnClickListener {
//            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL )
//            }
//            else{
//                startCall()
//            }
//        }
        return view
    }
    private fun startCall() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + number)
        startActivity(callIntent)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray)
    {
        if (requestCode == REQUEST_PHONE_CALL) {
            startCall()
        }
    }

}