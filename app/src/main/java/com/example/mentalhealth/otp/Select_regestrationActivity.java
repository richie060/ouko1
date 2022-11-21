package com.example.mentalhealth.otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mentalhealth.R;

public class Select_regestrationActivity extends AppCompatActivity {

    private TextView back;
    private Button patientReg, doctorReg,upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_regestration);

    back = findViewById(R.id.back);
    patientReg = findViewById(R.id.patientReg);
    doctorReg = findViewById(R.id.doctorReg);
//    upload = findViewById(R.id.upload);
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Select_regestrationActivity.this, Uploads.class);
//                startActivity(intent);
//
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_regestrationActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        patientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_regestrationActivity.this, Patient_Registration_Activity.class);
                startActivity(intent);
            }
        });
        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_regestrationActivity.this, Doctor_Registration_Activity.class);
                startActivity(intent);
            }
        });


    }
}