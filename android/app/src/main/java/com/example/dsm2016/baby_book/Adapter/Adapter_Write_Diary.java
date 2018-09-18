package com.example.dsm2016.baby_book.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.dsm2016.baby_book.Item.Item_Write_diary_Image;
import com.example.dsm2016.baby_book.Item.Item_write_diary;
import com.example.dsm2016.baby_book.R;

import java.util.ArrayList;

/**
 * Created by ghdth on 2018-05-02.
 */

public class Adapter_Write_Diary extends RecyclerView.Adapter<Adapter_Write_Diary.ViewHolder> {
    private ArrayList<Item_Write_diary_Image> mItem;
    private Context context;
    private LayoutInflater layoutInflater;
    // private RequestManager requestManager;
    public Adapter_Write_Diary(ArrayList<Item_Write_diary_Image> mItem, Context context){
        this.mItem=mItem;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wirte_diary,parent,false);
        ViewHolder holder=new ViewHolder(v);

        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.imageView.setImageResource(mItem.get(position).getImage());
        Glide.with(context).load(mItem.get(position).getUri()).into(holder.imageView);
        Log.d("ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴimage:",(mItem.get(position).getUri().toString()));
    }


    @Override
    public int getItemCount() {
        Log.d("mItem.size()",String.valueOf(mItem.size()));
        return mItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wirte_diary_iv);
        }


    }}
