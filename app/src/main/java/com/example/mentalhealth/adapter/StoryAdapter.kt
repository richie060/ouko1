package com.example.mentalhealth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mentalhealth.model.Story
import mentalhealth.R

class StoryAdapter(val context: Context, val storyList: ArrayList<Story>):
    RecyclerView.Adapter<StoryAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
         val view: View= LayoutInflater.from(context).inflate(R.layout.storyitem, parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
           val story =  storyList[position]

        holder.story.text = story.feedback
        holder.names.text = story.name
        holder.email.text = story.email
    }



    override fun getItemCount(): Int {
        return storyList.size

    }
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
           var email: TextView = itemView.findViewById(R.id.email)
           var  names: TextView = itemView.findViewById(R.id.names)
           val story: TextView = itemView.findViewById(R.id.story)
    }
}