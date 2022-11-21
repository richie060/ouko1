package com.example.mentalhealth.otp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mentalhealth.activities.ChatActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mentalhealth.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    private Button bookappointment;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.user_display_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = userList.get(position);
        holder.type.setText(user.getType());


        holder.bookappointment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(context, ChatActivity.class);
                n.putExtra("name", user.getName());
                n.putExtra("id", user.getId());
               context.startActivity(n);
            }

        });



//        if (user.getType().equals("patients")){
//            holder.bookappointment.setVisibility(View.VISIBLE);
//        }

        holder.username.setText(user.getName());
        holder.useremail.setText(user.getEmail());
        holder.type.setText(user.getType());
        holder.phonenumber.setText(user.getPhonenumber());


//holder.bookappointment2.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(context, EmailActivity.class);
//        context.startActivity(intent);
////        Log.i("Send SMS", "");
////        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
////
////        smsIntent.setData(Uri.parse("smsto:"));
////        smsIntent.setType("vnd.android-dir/mms-sms");
////        smsIntent.putExtra("address" , new String (user.phonenumber));
////        smsIntent.putExtra("sms_body" , "Test ");
////
////        try {
////            context.startActivity(smsIntent);
////            Log.i("Finished sending SMS...", "");
////        } catch (android.content.ActivityNotFoundException ex) {
////            Toast.makeText(context,
////                    "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
////        }
//    }
//});

        Glide.with(context).load(user.getProfilepictureurl()).into(holder.userprofileimage);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView userprofileimage;

        public TextView type,phonenumber,useremail,username;
        public Button bookappointment2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userprofileimage=itemView.findViewById(R.id.userProfileImage);
            phonenumber=itemView.findViewById(R.id.profile_PhoneNo);
            username=itemView.findViewById(R.id.username);
            useremail=itemView.findViewById(R.id.useremail);
            type=itemView.findViewById(R.id.profile_type);
            bookappointment2 = itemView.findViewById(R.id.buttonbookappointment);


        }
    }
}
