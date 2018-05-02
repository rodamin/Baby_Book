package com.example.dsm2016.baby_book.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dsm2016.baby_book.Item.Item_write_diary;
import com.example.dsm2016.baby_book.R;

import java.util.ArrayList;

/**
 * Created by ghdth on 2018-05-02.
 */

public class Adapter_Write_Diary extends RecyclerView.Adapter<Adapter_Write_Diary.ViewHolder> {
    private ArrayList<Item_write_diary> mItem;
    private Context context;
    private LayoutInflater layoutInflater;
    public Adapter_Write_Diary(ArrayList<Item_write_diary> mItem,Context context){
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
        holder.imageView.setImageResource(mItem.get(position).getImage());

    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wirte_diary_iv);
        }


    }}
