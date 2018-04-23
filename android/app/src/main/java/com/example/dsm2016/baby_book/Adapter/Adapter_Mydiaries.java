package com.example.dsm2016.baby_book.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dsm2016.baby_book.R;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Adapter_Mydiaries extends RecyclerView.Adapter {

    private Context context;
    private ArrayList mItems;

    private int lastPosition = -1;


    public Adapter_Mydiaries(ArrayList items, Context mContext){
        mItems = items;
        context = mContext;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diaries,parent,false);
        ViewHolder holder=new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.diaries_cover);

        }

    }
    private void setAnimation(View viewToAnimate,int postion){
        if(postion>lastPosition){
            Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition=postion;
        }
    }
}
