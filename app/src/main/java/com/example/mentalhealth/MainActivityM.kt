package com.example.mentalhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mentalhealth.Fragment.Home
import com.example.mentalhealth.Fragment.Resources
import com.example.mentalhealth.otp.MainActivity
import com.example.mentalhealth.otp.ProfileActivity
import kotlinx.android.synthetic.main.activity_mainm.*
import mentalhealth.R

@Suppress("DEPRECATION")
class MainActivityM : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_mainm)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val home = Home()
//        val contact = Contact()
//        val meditation = Meditation2()
        val profile = FAQFragment()
        val resources = Resources()

        setCurrentFragment(home)

        bottomnavbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mihome -> setCurrentFragment(home)
                R.id.mifaq -> setCurrentFragment(profile)
                R.id.mimeditation -> setCurrentFragment(resources)
                R.id.miprofile -> {
                    val intent = Intent(this@MainActivityM, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.micontact ->{
                    val intent = Intent(this@MainActivityM, MainActivity::class.java)
                    startActivity(intent)

//                    val linearLayout: LinearLayout = findViewById(R.id.frlayout)
//                    val view: View = layoutInflater.inflate(R.layout.contact, null)
//                    linearLayout.addView(view)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frlayout, fragment)
            commit()
        }

}