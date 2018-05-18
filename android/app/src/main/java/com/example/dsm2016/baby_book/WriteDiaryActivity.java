package com.example.dsm2016.baby_book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dsm2016.baby_book.Adapter.Adapter_Write_Diary;
import com.example.dsm2016.baby_book.Item.Item_write_diary;

import java.util.ArrayList;

public class WriteDiaryActivity extends BaseActivity {

    private Button btn_add_picture, btn_save;
    LayoutInflater mInflater;
    LinearLayout mRootLinear;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Item_write_diary> mitem;
    private RecyclerView.Adapter madapter;
    ImageView img;
    int temp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        btn_add_picture = (Button)findViewById(R.id.btn_add_picture);
        btn_save = (Button)findViewById(R.id.btn_save);

        mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        mRootLinear = (LinearLayout)findViewById(R.id.viewgroup_add_picture);
        img= (ImageView)mInflater.inflate(R.layout.item_add_picture, mRootLinear, false);

        btn_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRootLinear.addView(img);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.write_diary_rv);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mitem=new ArrayList<>();
        madapter=new Adapter_Write_Diary(mitem,getApplicationContext());
        recyclerView.setAdapter(madapter);
        mitem.add(new Item_write_diary(R.drawable.test2));
        mitem.add(new Item_write_diary(R.drawable.test3));
        mitem.add(new Item_write_diary(R.drawable.test));
        mitem.add(new Item_write_diary(R.drawable.test3));


    }
}
