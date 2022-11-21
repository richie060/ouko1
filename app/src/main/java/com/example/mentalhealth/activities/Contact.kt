package com.example.mentalhealth.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mentalhealth.Fragment.Home
import com.example.mentalhealth.Fragment.Profile
import com.example.mentalhealth.Fragment.Resources
import com.example.mentalhealth.adapter.UserAdapter
import com.example.mentalhealth.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_mainm.*
import mentalhealth.R

class Contact: AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.counsellor)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val home = Home()
//        val contact = Contact()
//        val meditation = Meditation2()
        val profile = Profile()
        val resources = Resources()

        bottomnavbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mihome -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frlayout3, home)
                    commit()
                }

//                R.id.miresources ->
//                    supportFragmentManager.beginTransaction().apply {
//                        replace(R.id.frlayout3, resources)
//                        commit()
//                }

                R.id.miprofile -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frlayout3, profile)
                    commit()
                }
                R.id.micontact ->  {
                    val intent = Intent(this@Contact, Contact::class.java)
                    startActivity(intent)
                }

            }
            true
        }
        supportActionBar

        mAuth = FirebaseAuth.getInstance()
        mDbRef =FirebaseDatabase.getInstance().getReference()


        userList = ArrayList()
        adapter = UserAdapter(this, userList)
        userRecyclerView = findViewById(R.id.userRecyclerView)
        progressBar = findViewById<ProgressBar>(R.id.progressbar)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter



        mDbRef.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }
//                    userList.add(currentUser!!)
                }
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logoutmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            val intent= Intent(this@Contact, Login::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }




}