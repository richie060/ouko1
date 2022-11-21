package com.example.mentalhealth;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.mentalhealth.audio.UploadSongActivity
import com.example.mentalhealth.photos.MemesUploadActivity
import com.example.mentalhealth.player.UploadMovie
import com.example.mentalhealth.positivenotes.Success
import kotlinx.android.synthetic.main.activity_uploads.*
import mentalhealth.R

class Uploads : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_uploads)

        uploadSongs1.setOnClickListener {
            val intent = Intent(this, UploadSongs::class.java)
            startActivity(intent)
        }
        uploadVideos.setOnClickListener {
            val intent = Intent(this, UploadMovie::class.java)
            startActivity(intent)
        }
        addstory.setOnClickListener {
            val intent = Intent(this, Success::class.java)
            startActivity(intent)
        }
//        addcourse.setOnClickListener {
//            val intent = Intent(this, com.example.mentalhealth.videos.UploadMovie::class.java)
//            startActivity(intent)
//        }
//        addmeme.setOnClickListener {
//            val intent = Intent(this, MemesUploadActivity::class.java)
//            startActivity(intent)
//        }
    }




}