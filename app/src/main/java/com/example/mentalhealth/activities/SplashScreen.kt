package com.example.mentalhealth.activities
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import mentalhealth.R


class SplashScreen : AppCompatActivity() {


    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var logo = findViewById<View>(R.id.SplashScreenImage) as ImageView
        Handler().postDelayed({
            val i = Intent(this@SplashScreen, Login::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut.toLong())
        val myanim = AnimationUtils.loadAnimation(this, R.anim.splashanimation)
        logo!!.startAnimation(myanim)
    }

    companion object {
        private const val splashTimeOut = 3000
    }
}

//
//        logo = findViewById<View>(R.id.SplashScreenImage) as ImageView
//        Handler().postDelayed({
//            val i = Intent(this@SplashScreen, MainActivityM::class.java)
//            startActivity(i)
//            finish()
//        }, splashTimeOut.toLong())
//        val myanim = AnimationUtils.loadAnimation(this, R.anim.splashanimation)
//        logo!!.startAnimation(myanim)
//    }
//
//    companion object {
//        private const val splashTimeOut = 3000
//    }
//}
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.view.WindowManager
//import androidx.appcompat.app.AppCompatActivity
//
//class SplashScreen : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_screen)
//
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//
//        // we used the postDelayed(Runnable, time) method
//        // to send a message with a delayed time.
//        Handler().postDelayed({
//            val intent = Intent(this, MainActivityM::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000) // 3000 is the delayed time in milliseconds.
//    }
//}