package com.example.mentalhealth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import mentalhealth.R;

public class Games extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView game1, game2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Games ");

        game1 = findViewById(R.id.cardviewgame1);
        game2 = findViewById(R.id.cardviewgame2);
//        email = findViewById(R.id.profile_email);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
