package com.example.mentalhealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mentalhealth.videos.MainActivity;

import mentalhealth.R;

public class MeditationActivity extends AppCompatActivity {



    private CardView watch1,cardialmeditation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        watch1 = findViewById(R.id.meditation);
        cardialmeditation = findViewById(R.id.cardialmeditation);
//                 relax = findViewById(R.id.cvrelax);
//                 axious = findViewById(R.id.cvaxious);

        watch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeditationActivity.this, MainActivity.class);
                startActivity(intent);
//                Uri uri = Uri.parse("https://youtu.be/ClqPtWzozXs?t=2"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
            }
        });

        cardialmeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://youtu.be/KQOAVZew5l8"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
//        axiety.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MeditationActivity.this, MusicActivity.class);
//                startActivity(intent);
//            }
//        });
//        relax.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MeditationActivity.this, MusicActivity.class);
//                startActivity(intent);
//            }
//        });
//        axious.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MeditationActivity.this, MusicActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}