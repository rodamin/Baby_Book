package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dsm2016.baby_book.Adapter.Adapter_Mydiaries;
import com.example.dsm2016.baby_book.Adapter.Adapter_ShowDiaries;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;
import com.example.dsm2016.baby_book.Item.Item_ShowDiaries;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyDiaryActivity extends BaseActivity {

    private RelativeLayout relative_all;
    private TextView text_story, text_graph, text_video, text_babyinfo,text_title;
    private ImageButton btn_menu, btn_story, btn_graph, btn_video, btn_babyinfo;
    private FloatingActionButton float_add_diary;
    boolean temp = false;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item_ShowDiaries> item_showDiaries;
    Item_ShowDiaries item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        relative_all = (RelativeLayout)findViewById(R.id.relative_all);
        text_story = (TextView)findViewById(R.id.text_story);
        text_graph = (TextView)findViewById(R.id.text_graph);
        text_video = (TextView)findViewById(R.id.text_video);
        text_babyinfo = (TextView)findViewById(R.id.text_babyinfo);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);
        btn_story = (ImageButton)findViewById(R.id.btn_story);
        btn_graph = (ImageButton)findViewById(R.id.btn_graph);
        btn_video = (ImageButton)findViewById(R.id.btn_video);
        btn_babyinfo = (ImageButton)findViewById(R.id.btn_baby_info);
        float_add_diary = (FloatingActionButton)findViewById(R.id.float_add_diary);
        text_title=(TextView)findViewById(R.id.text_title);

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

        String title=intent.getStringExtra("title");
        if(!title.isEmpty()){
            text_title.setText(title);
        }
        // 메뉴 클릭 시 인텐트

        btn_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStoryActivity.class);
                startActivity(intent);
                Log.d("xxx", "xxx");
            }
        });

        btn_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(intent);
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectVideo.class);
                startActivity(intent);
            }
        });

        btn_babyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Change_BabyInfoActivity.class);
                startActivity(intent);
            }
        });

        float_add_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDiaryActivity.class);
                startActivity(intent);
                ani_menu_off();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                temp =! temp;
                Log.d("xxx", temp+"");

                if(temp == false) {
                    ani_menu_off();
                } else {
                    ani_menu_on();
                }

            }
        });
    }

    public void ani_menu_on() {
        Animation menu_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.open_menu_translate);
        menu_open.setFillAfter(true);

        relative_all.setBackgroundColor(Color.parseColor("#66000000"));
        relative_all.setAnimation(menu_open);
        btn_story.setVisibility(View.VISIBLE);
        btn_story.startAnimation(menu_open);
        btn_graph.setVisibility(View.VISIBLE);
        btn_graph.startAnimation(menu_open);
        btn_video.setVisibility(View.VISIBLE);
        btn_video.startAnimation(menu_open);
        btn_babyinfo.setVisibility(View.VISIBLE);
        btn_babyinfo.startAnimation(menu_open);
        text_story.setVisibility(View.VISIBLE);
        text_story.setAnimation(menu_open);
        text_graph.setVisibility(View.VISIBLE);
        text_graph.setAnimation(menu_open);
        text_video.setVisibility(View.VISIBLE);
        text_video.setAnimation(menu_open);
        text_babyinfo.setVisibility(View.VISIBLE);
        text_babyinfo.setAnimation(menu_open);
    }

    public void ani_menu_off() {

        Animation menu_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.close_menu_translate);
        menu_close.setFillAfter(true);

        relative_all.setBackgroundColor(Color.TRANSPARENT);
        relative_all.setAnimation(menu_close);
        btn_story.setVisibility(View.INVISIBLE);
        btn_story.startAnimation(menu_close);
        btn_graph.setVisibility(View.INVISIBLE);
        btn_graph.startAnimation(menu_close);
        btn_video.setVisibility(View.INVISIBLE);
        btn_video.startAnimation(menu_close);
        btn_babyinfo.setVisibility(View.INVISIBLE);
        btn_babyinfo.startAnimation(menu_close);
        text_story.setVisibility(View.INVISIBLE);
        text_story.setAnimation(menu_close);
        text_graph.setVisibility(View.INVISIBLE);
        text_graph.setAnimation(menu_close);
        text_video.setVisibility(View.INVISIBLE);
        text_video.setAnimation(menu_close);
        text_babyinfo.setVisibility(View.INVISIBLE);
        text_babyinfo.setAnimation(menu_close);
    }
}

