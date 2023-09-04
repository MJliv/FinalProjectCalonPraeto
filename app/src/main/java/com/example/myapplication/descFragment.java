package com.example.myapplication;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class descFragment extends Fragment {

    public descFragment() {

    }

    String desc,photo;
    public descFragment(String desc, String photo ){
        this.desc = desc;
        this.photo = photo;
    }

    Button btn_back;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_desc, container, false);

        ImageView imageholder = view.findViewById(R.id.fullImageView);
        TextView descholder = view.findViewById(R.id.descholder);

        descholder.setText(desc);
        Glide.with(getContext()).load(photo).into(imageholder);

        btn_back = view.findViewById(R.id.btn_backHome);
        btn_back.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity)getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new imageFragment()).addToBackStack(null).commit();
        });

        return view;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new imageFragment()).addToBackStack(null).commit();

    }

}