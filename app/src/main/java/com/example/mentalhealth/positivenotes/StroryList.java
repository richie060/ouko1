package com.example.mentalhealth.positivenotes;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mentalhealth.R;

public class StroryList extends AppCompatActivity {

    ProgressBar progressBar;
    private Toolbar toolBar;
    RecyclerView recyclerView;
    ArrayList<SS> list;
    DatabaseReference databaseRef;
    StoryAdapter adapter;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(StroryList.this, MainActivityM.class));
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strory_list);

        progressBar = findViewById(R.id.progressBars);

        toolBar = findViewById(R.id.toolBars1);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Success stories ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recyclerview_ShowStory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                list = new ArrayList<>();
        databaseRef= FirebaseDatabase.getInstance().getReference("story");
                adapter=new StoryAdapter(this,list);
                recyclerView.setAdapter(adapter);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SS story = dataSnapshot.getValue(SS.class);
                    list.add(story);
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}