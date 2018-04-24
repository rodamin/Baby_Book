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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;
import com.example.dsm2016.baby_book.R;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Adapter_Mydiaries extends RecyclerView.Adapter<Adapter_Mydiaries.ViewHolder>  {
    private ItemClickListener mClickListener;


    private ArrayList<Item_Mydairies> mItems;
    private Context context;

    public Adapter_Mydiaries(ArrayList<Item_Mydairies> items){
        this.mItems = items;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diaries,parent,false);
        ViewHolder holder=new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mItems.get(position).getImage());
       /* Glide.with(context).load(R.drawable.background_main)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(45,0,
                        RoundedCornersTransformation.CornerType.BOTTOM))).into(holder.imageView);*/
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_diaries);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mClickListener!=null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

    }

    private interface ItemClickListener {
        void onItemClick(View view, int adapterPosition);
    }
}
