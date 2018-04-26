package com.example.dsm2016.baby_book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WriteDiaryActivity extends BaseActivity {

    private Button btn_add_picture;
    LayoutInflater mInflater;
    LinearLayout mRootLinear;
    ImageView img;
    int temp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        btn_add_picture = (Button)findViewById(R.id.btn_add_picture);

        mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        mRootLinear = (LinearLayout)findViewById(R.id.viewgroup_add_picture);
        img= (ImageView)mInflater.inflate(R.layout.item_add_picture, mRootLinear, false);

        btn_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRootLinear.addView(img);
            }
        });
    }
}
