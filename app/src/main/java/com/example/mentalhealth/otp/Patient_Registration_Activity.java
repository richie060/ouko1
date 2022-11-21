package com.example.mentalhealth.otp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import mentalhealth.R;

public class Patient_Registration_Activity extends AppCompatActivity {

    private TextView regpageQuestion;
    private TextInputEditText registrationFullname, registrationIDnumber,registrationPhoneNumber,
            loginEmail, loginPassord;

    private Button regButton;
    private CircleImageView profileImage;
    private Uri resultUri;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);


        regpageQuestion = findViewById(R.id.regpageQuestion);

        regpageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patient_Registration_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registrationFullname = findViewById(R.id.Regfullname);
        registrationIDnumber = findViewById(R.id.RgLn);
        registrationPhoneNumber = findViewById(R.id.RegPhoneNo);
        loginPassord = findViewById(R.id.loginPassword);
        regButton = findViewById(R.id.RegButton);
        profileImage = findViewById(R.id.profileImage);
        loginEmail= findViewById(R.id.loginEmail);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();




        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = loginEmail.getText().toString().trim();
                final String password = loginPassord.getText().toString().trim();
                final String fullName = registrationFullname.getText().toString().trim();
                final String Idnumber = registrationPhoneNumber.getText().toString().trim();
                final String PhoneNumber = registrationPhoneNumber.getText().toString().trim();



                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    loginPassord.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registrationFullname.setError("Fullname  is required");
                    return;
                }
                if (TextUtils.isEmpty(Idnumber)){
                    registrationIDnumber.setError("Last name is required");
                    return;
                }
                if (TextUtils.isEmpty(PhoneNumber)){
                    registrationPhoneNumber.setError("PhoneNumber  is required");
                    return;
                }

                if (resultUri == null){
                    Toast.makeText(Patient_Registration_Activity.this, "Profile is required", Toast.LENGTH_SHORT).show();
                }else {
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        String error = task.getException().toString();
                                        Toast.makeText(Patient_Registration_Activity.this, "Error Occured" + error, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        String currentUserId = mAuth.getCurrentUser().getUid();
                                        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                                .child("users").child(currentUserId);
                                        HashMap userInfo = new HashMap();
                                        userInfo.put("name",fullName);
                                        userInfo.put("email",email);
                                        userInfo.put("lastname",Idnumber);
                                        userInfo.put("phonenumber",PhoneNumber);
                                        userInfo.put("type","patient");
                                        userInfo.put("id",currentUserId);

                                        userDatabaseRef.updateChildren(userInfo)
                                                .addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(Patient_Registration_Activity.this, "Details Set Successfully", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            Toast.makeText(Patient_Registration_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                        }
                                                        finish();
                                                        loader.dismiss();
                                                    }
                                                });
                                        if (resultUri != null){
                                            final StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profile pictures")
                                                    .child(currentUserId);
                                            Bitmap bitmap = null;
                                            try {
                                                bitmap = MediaStore.Images.Media.getBitmap(getApplication()
                                                        .getContentResolver(), resultUri);
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
                                            byte[] data = byteArrayOutputStream.toByteArray();
                                            UploadTask uploadTask = filepath.putBytes(data);

                                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    finish();
                                                    return;
                                                }
                                            });

                                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    if (taskSnapshot.getMetadata() != null){
                                                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                String imageurl = uri.toString();
                                                                Map  newImagemap = new HashMap();
                                                                newImagemap.put("profilepictureurl",imageurl);

                                                                userDatabaseRef.updateChildren(newImagemap).addOnCompleteListener(new OnCompleteListener() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task task) {
                                                                        if (task.isSuccessful()){
//                                                                            Intent intent = new Intent(Patient_Registration_Activity.this, LoginActivity.class);
//                                                                            startActivity(intent);
                                                                            Toast.makeText(Patient_Registration_Activity.this, "Reg success", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else {
                                                                            Toast.makeText(Patient_Registration_Activity.this, "Reg Failed", Toast.LENGTH_SHORT).show();
                                                                        }

                                                                    }
                                                                });
                                                                finish();
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                            Intent intent = new Intent(Patient_Registration_Activity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                            loader.dismiss();
                                        }
                                    }
                                }
                            });
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data!=null){
            resultUri = data.getData();
            profileImage.setImageURI(resultUri);

        }
    }
}