package com.example.android.internshalaproject;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolde> {
    Context context;
    private ArrayList<String> imageData = new ArrayList<String>();


    public MyAdapter(ArrayList<String> imageData, FragmentActivity activity) {
        this.imageData = imageData;
        this.context = activity;
    }

    @Override
    public MyAdapter.MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_single_image_row, parent, false);

        return new MyViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolde holder, int position) {
        String data = imageData.get(position);
        if (data != null){
            Glide.with(context).load(data).into(holder.singleImageView);

        }else {
            Toast.makeText(context, "Images Empty", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }

    public class MyViewHolde extends RecyclerView.ViewHolder {
        ImageView singleImageView;

        public MyViewHolde(View itemView) {
            super(itemView);
            singleImageView = (ImageView) itemView.findViewById(R.id.singleImageView);
        }
    }
}
