package com.example.mentalhealth.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealth.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import mentalhealth.R

class Counsellor_registration : AppCompatActivity() {


    private lateinit var editname: EditText
    private lateinit var editEmail: EditText
    private lateinit var editlastname: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmp: EditText
    private lateinit var btnLogin: TextView
    private  lateinit var btnRegister: Button
    private  lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var loader: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        editname = findViewById(R.id.editname)
        editEmail = findViewById(R.id.editusername)
        editPassword = findViewById(R.id.editpassword)
        editConfirmp = findViewById(R.id.editconfirmpassword)
        editlastname = findViewById(R.id.editlastname)
        btnLogin= findViewById(R.id.btnlogin)
        btnRegister = findViewById(R.id.btnregister)
        loader = ProgressDialog(this)

        btnRegister.setOnClickListener {
            if (TextUtils.isEmpty(editname.text.toString())){
                editname.setError("Please enter the first name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(editlastname.text.toString())){
                editlastname.setError("Please enter the Last name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(editEmail.text.toString())){
                editEmail.setError("Please enter the  User name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(editPassword.text.toString())){
                editPassword.setError("Please enter the  password")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(editConfirmp.text.toString())){
                editConfirmp.setError("Please enter the Last name")
                return@setOnClickListener
            }
            val name = editname.text.toString()
            val lastname = editlastname.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val type = "therapist"
            Signup(name,lastname,email,password,type)
        }
        btnlogin.setOnClickListener {
            val intent = Intent(this@Counsellor_registration, Login::class.java)
            startActivity(intent)
        }
    }
    private  fun Signup(name:String, lastname: String, email : String, password: String,type: String){
        loader.setMessage("Registration  in progress")
        loader.setCanceledOnTouchOutside(false)
        loader.show()
        //logic For creating User
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    addUserToDatabase(name,lastname, email,type, mAuth.currentUser?.uid!!)
                    Toast.makeText(this@Counsellor_registration,"Successfully Registered", Toast.LENGTH_LONG).show()
                    val  intent = Intent(this@Counsellor_registration, Login::class.java)
                    loader.dismiss()
                    finish()
                    startActivity(intent)
                }else{
                    loader.dismiss()
                    Toast.makeText(this@Counsellor_registration,"Registration failed Please try Again",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun addUserToDatabase(name: String,lastname: String, email: String, uid: String, type: String){
        mDbRef= FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,lastname,email,uid,type))
    }
}