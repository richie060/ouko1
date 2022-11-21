package com.example.mentalhealth.photos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mentalhealth.R;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {

    private Context context;
    private List<model> memelist;
    private Button bookappointment;

    public myadapter(Context context, List<model> memelist) {
        this.context = context;
        this.memelist = memelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.memedesign,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final model meme = memelist.get(position);

        holder.title.setText(meme.getFilename());

        Glide.with(context).load(meme.getFileurl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return memelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;

        public TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.img1);
            title=itemView.findViewById(R.id.header);


        }
    }
}
