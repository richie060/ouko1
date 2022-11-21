package com.example.mentalhealth.positivenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mentalhealth.R;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MYViewHolder> {
   Context context;
   ArrayList<SS> list;

    public StoryAdapter(Context context, ArrayList<SS> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.storyentry,parent,false);

        return new MYViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {
                  SS story = list.get(position);
//                  holder.name.setText(story.getName2());
//        holder.email.setText(story.getEmail2());
        holder.story.setText(story.getStory());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MYViewHolder extends  RecyclerView.ViewHolder {
         TextView name, email, story;
        public MYViewHolder(@NonNull View itemView) {
            super(itemView);
//            name = itemView.findViewById(R.id.names);
//            email=itemView.findViewById(R.id.emails);
            story = itemView.findViewById(R.id.storys);


        }
    }
}
