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
import kotlinx.android.synthetic.main.activity_uploadsongs.*
import mentalhealth.R

class UploadSongs : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_uploadsongs)

        upload1.setOnClickListener {
            val intent = Intent(this, com.example.mentalhealth.Sleeping.UploadSongActivity::class.java)
            startActivity(intent)
        }
        upload2.setOnClickListener {
            val intent = Intent(this, com.example.mentalhealth.anxiety.UploadSongActivity::class.java)
            startActivity(intent)
        }
        upload3.setOnClickListener {
            val intent = Intent(this, com.example.mentalhealth.sad.UploadSongActivity::class.java)
            startActivity(intent)
        }
        upload4.setOnClickListener {
            val intent = Intent(this, UploadSongActivity::class.java)
            startActivity(intent)
        }

    }




}