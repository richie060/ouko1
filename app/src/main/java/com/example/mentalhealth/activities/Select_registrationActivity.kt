package com.example.mentalhealth.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_registration.*
import mentalhealth.R

class Select_registrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_registration)

        patientReg.setOnClickListener {
            val intent = Intent(this@Select_registrationActivity, Register::class.java)
            startActivity(intent)
        }

        counsellorReg.setOnClickListener {
            val intent =
                Intent(this@Select_registrationActivity, Counsellor_registration::class.java)
            startActivity(intent)
        }
        back.setOnClickListener {
            val intent = Intent(this@Select_registrationActivity, Login::class.java)
            startActivity(intent)

        }
    }
}