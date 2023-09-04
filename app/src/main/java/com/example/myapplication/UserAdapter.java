package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class UserAdapter extends FirebaseRecyclerAdapter<ImageModel, UserAdapter.ViewHolder> {
    public UserAdapter(@NonNull FirebaseRecyclerOptions<ImageModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ImageModel model) {
        holder.desctext.setText(model.getDesc());
        Glide.with(holder.img1.getContext()).load(model.getPhoto()).into(holder.img1);
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new descFragment(model.getDesc(), model.getPhoto())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        TextView desctext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            desctext = itemView.findViewById(R.id.desctext);
         }
    }
}
