package com.example.dsm2016.baby_book;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyDiaryActivity extends BaseActivity {

    private LinearLayout linear_title;
    private RelativeLayout relative_all;
    private TextView text_title;
    private ImageButton btn_menu, btn_story, btn_graph, btn_video;
    boolean temp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        linear_title = (LinearLayout)findViewById(R.id.linear_title);
        relative_all = (RelativeLayout)findViewById(R.id.relative_all);
        text_title = (TextView)findViewById(R.id.text_title);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);
        btn_story = (ImageButton)findViewById(R.id.btn_story);
        btn_graph = (ImageButton)findViewById(R.id.btn_graph);
        btn_video = (ImageButton)findViewById(R.id.btn_video);

        final Animation menu_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.open_menu_translate);
        final Animation menu_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.close_menu_translate);

        menu_open.setFillAfter(true);
        menu_close.setFillAfter(true);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                temp =! temp;
                Log.d("xxx", temp+"");

                if(temp == false) {
                    linear_title.setBackgroundResource(android.R.color.transparent);
                    relative_all.setBackgroundResource(android.R.color.transparent);
                    btn_story.setVisibility(View.INVISIBLE);
                    btn_story.startAnimation(menu_close);
                    btn_graph.setVisibility(View.INVISIBLE);
                    btn_graph.startAnimation(menu_close);
                    btn_video.setVisibility(View.INVISIBLE);
                    btn_video.startAnimation(menu_close);
                    text_title.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    linear_title.setBackgroundResource(R.drawable.cover_black);
                    linear_title.startAnimation(menu_open);
                    relative_all.setBackgroundResource(R.drawable.cover_black);
                    relative_all.startAnimation(menu_open);
                    btn_story.setVisibility(View.VISIBLE);
                    btn_story.startAnimation(menu_open);
                    btn_graph.setVisibility(View.VISIBLE);
                    btn_graph.startAnimation(menu_open);
                    btn_video.setVisibility(View.VISIBLE);
                    btn_video.startAnimation(menu_open);
                    text_title.setTextColor(Color.parseColor("#999999"));
                }

            }
        });

        btn_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "click");
            }
        });

    }
}


//    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
//        btn_boy.startAnimation(anim);
//                anim.setFillAfter(true);