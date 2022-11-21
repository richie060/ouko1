package com.example.mentalhealth.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealth.MainActivityM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import mentalhealth.R

class Login : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: TextView
    private  lateinit var mAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var loader: ProgressDialog


    private var authStateListener: AuthStateListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()


        authStateListener = AuthStateListener {
            val user = mAuth.currentUser
            if (user != null) {
                val intent = Intent(this@Login, Contact::class.java)
                startActivity(intent)
                finish()
            }
        }

        editEmail = findViewById(R.id.editusername)
        editPassword = findViewById(R.id.editpassword)
        btnLogin= findViewById(R.id.btnlogin)
        btnRegister = findViewById(R.id.btnregister)

        loader = ProgressDialog(this)


        btnRegister.setOnClickListener {
            val intent =  Intent(this@Login, Select_registrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            login(email,password);

            if (TextUtils.isEmpty(editEmail.text.toString())){
                editEmail.setError("Please enter username")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(editPassword.text.toString())){
                editPassword.setError("Please enter your password")
                    return@setOnClickListener
            }

        }
    }
    private fun login(email: String, password: String){

        loader.setMessage("Log in in progress")
        loader.setCanceledOnTouchOutside(false)
        loader.show()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(this@Login,"Logged in successfully",Toast.LENGTH_LONG).show()
                    val intent = Intent(this@Login, Contact::class.java)
                    loader.dismiss()
                    finish()
                    startActivity(intent)
                } else{
                    loader.dismiss()
                    Toast.makeText(this@Login, "Login failed",Toast.LENGTH_SHORT).show()
                }
            }
    }


    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(authStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(authStateListener!!)
    }
}