package com.sametkemalozdemir.instaclonefirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {
    private ArrayList<String> userMail;
    private  ArrayList<String> userImages;
    private ArrayList<String>userComment;

    public FeedRecyclerAdapter(ArrayList<String> userMail, ArrayList<String> userImages, ArrayList<String> userComment) {
        this.userMail = userMail;
        this.userImages = userImages;
        this.userComment = userComment;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycle_row,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
            holder.mail.setText(userMail.get(position));
            holder.comment.setText(userComment.get(position));
            Picasso.get().load(userImages.get(position)).into(holder.post);
    }

    @Override
    public int getItemCount() {
        return userMail.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        ImageView post;
        TextView mail,comment;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            comment= itemView.findViewById(R.id.recyclerView_row_comment);
            mail=itemView.findViewById(R.id.recyclerView_row_userMail);
            post=itemView.findViewById(R.id.recyclerView_row_postPhoto);
        }
    }




}
