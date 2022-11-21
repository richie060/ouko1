package com.example.mentalhealth.positivenotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mentalhealth.R;

public class Success extends AppCompatActivity {

    private Toolbar toolbar;
    Button btnInsert;
    EditText name, email,story;
    DatabaseReference databaseSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        btnInsert = findViewById(R.id.btninsert);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email2);
        story = findViewById(R.id.feedback);

        toolbar = findViewById(R.id.toolBars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload  Success stories ");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseSS = FirebaseDatabase.getInstance().getReference();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });

    }

    private void InsertData() {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userstory = story.getText().toString();
        String id = databaseSS.push().getKey();
        SS story = new SS(username,useremail,userstory);
        databaseSS.child("story").child(id).setValue(story)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Success.this, "Your note has been saved Successfully", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            Intent intent = new Intent(Success.this, StroryList.class);
                            startActivity(intent);
                        }


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