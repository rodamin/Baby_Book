package com.example.dsm2016.baby_book.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.DiariesActivity;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;
import com.example.dsm2016.baby_book.R;
import com.example.dsm2016.baby_book.Sever.APIinterface;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Adapter_Mydiaries extends RecyclerView.Adapter<Adapter_Mydiaries.ViewHolder>  {

    private LayoutInflater layoutInflater;
    private ArrayList<Item_Mydairies> mItems;
    private Context context;
    private Item_Mydairies item;
    public Adapter_Mydiaries(ArrayList<Item_Mydairies> items,Context context){
        this.mItems = items;
        Log.d("items size", mItems.size() + "");
        this.context=context;
    }

    private static Context sContext;
    private Retrofit retrofit;
    private APIinterface apIinterface;
    Realm mRealm;
    DB_Code db_qna;

    @Override
    public Adapter_Mydiaries.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diaries,parent,false);
        Adapter_Mydiaries.ViewHolder holder=new Adapter_Mydiaries.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("position", position + "");
        holder.imageView.setImageResource(mItems.get(position).getImage());
        Log.d("imageVeiw ㄴㄴㄴㄴㄴㄴㄴ:",String.valueOf(mItems.get(position).getImage())+" "+mItems.get(position).getBaby_name());
       /* Glide.with(context).load(R.drawable.background_main)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(45,0,
                        RoundedCornersTransformation.CornerType.BOTTOM))).into(holder.imageView);*/
//        View layout_view=layoutInflater.inflate(R.layout.activity_diaries,null,true);

       holder.cardView.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               Toast.makeText(context,"하하"+position,Toast.LENGTH_LONG).show();
               Log.d("하하",String.valueOf(position));
           }
       });

    }


    @Override public int getItemCount() {
        Log.d("mItems.size()ㄴㄴㄴㄴㄴㄴ:",String.valueOf(mItems.size()));
        return mItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_diaries);
            cardView=(CardView)itemView.findViewById(R.id.diaries_cover);
        }


    }
    public void deleteItem(int index){
        mItems.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
        notifyItemRangeChanged(index,mItems.size());
        Log.d("삭제",String.valueOf(index));

        mRealm = Realm.getDefaultInstance();
        RealmResults<DB_Code> results = mRealm.where(DB_Code.class).findAll();

        for(int i = 0; i < results.size(); i++){
            db_qna = results.get(i);
            Log.d("db_qna", "protocol : " + db_qna);
        }

        int code = db_qna.getCode();
        Log.d("getCode", code+"");

        String baby_name = "ParkHaeBin";

        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).addConverterFactory(GsonConverterFactory.create()).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.baby_delete(baby_name, code);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("baby_call 통신","성공");
                    Log.d("onResponse: ", response.body().toString());
                    Toast.makeText((Activity) sContext.getApplicationContext(),"아기들 불러오기 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText((Activity) sContext.getApplicationContext(),"아기들 불러오기 실패",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)  {
                Log.d("연결","실패"+t.getMessage());
            }
        });

    }
}
