package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dsm2016.baby_book.Adapter.Adapter_ShowDiaries;
import com.example.dsm2016.baby_book.Item.Item_ShowDiaries;

import java.util.ArrayList;

public class SelectVideo extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item_ShowDiaries> item_showDiaries;

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_video);

        // Intent
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectMusic.class);
                startActivity(intent);
            }
        });

        // 리사이클러뷰
        mRecyclerView=(RecyclerView)findViewById(R.id.showdiaries_rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));


        item_showDiaries=new ArrayList<>();
        mAdapter=new Adapter_ShowDiaries(item_showDiaries, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);


        item_showDiaries.add(new Item_ShowDiaries(R.drawable.background_main));
        item_showDiaries.add(new Item_ShowDiaries(R.drawable.test));
        item_showDiaries.add(new Item_ShowDiaries(R.drawable.test2));
        item_showDiaries.add(new Item_ShowDiaries(R.drawable.test3));

        Intent intent = getIntent();
        int image = intent.getIntExtra("image",0);
        Log.d("dfdfdf",Integer.toString(image));
    }
}
