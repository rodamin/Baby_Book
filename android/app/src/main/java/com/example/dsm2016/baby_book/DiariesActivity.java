package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dsm2016.baby_book.Adapter.Adapter_Mydiaries;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DiariesActivity extends BaseActivity  {

    private ImageView preview;
    private Button btn_calendar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item_Mydairies> item_mydairies;
    Item_Mydairies item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaries);

        btn_calendar = (Button)findViewById(R.id.btn_calendar);

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BabyInfoActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.diaries_rv);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        item_mydairies=new ArrayList<>();
        madapter=new Adapter_Mydiaries(item_mydairies,getApplicationContext());
        recyclerView.setAdapter(madapter);


        item_mydairies.add(new Item_Mydairies(R.drawable.background_main));
        item_mydairies.add(new Item_Mydairies(R.drawable.test));
        item_mydairies.add(new Item_Mydairies(R.drawable.test2));
        item_mydairies.add(new Item_Mydairies(R.drawable.test3));

       Intent intent=getIntent();
        int image=intent.getIntExtra("image",0);
        Log.d("dfdfdf",Integer.toString(image));
        //preview=(ImageView)findViewById(R.id.preview);
        //preview.setImageResource(item_mydairies.get(image).getImage());

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(),recyclerView,new RecyclerViewClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                preview=(ImageView)findViewById(R.id.preview);
                preview.setImageResource(item_mydairies.get(position).getImage());

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


}
