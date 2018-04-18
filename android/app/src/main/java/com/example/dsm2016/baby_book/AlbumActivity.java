package com.example.dsm2016.baby_book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class AlbumActivity extends BaseActivity {

    private ImageView preview;
    private Button btn_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        preview = (ImageView)findViewById(R.id.preview);
        preview.setImageResource(R.drawable.preview_example);

        btn_calendar = (Button)findViewById(R.id.btn_calendar);

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

    }


}
