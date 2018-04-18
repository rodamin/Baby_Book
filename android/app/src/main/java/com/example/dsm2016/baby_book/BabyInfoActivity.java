package com.example.dsm2016.baby_book;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class BabyInfoActivity extends BaseActivity implements View.OnClickListener{

    private ImageButton btn_boy, btn_girl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);

        btn_boy = (ImageButton)findViewById(R.id.btn_boy);
        btn_girl = (ImageButton)findViewById(R.id.btn_girl);

        btn_boy.setOnClickListener(this);
        btn_girl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_boy:
                btn_boy.setBackgroundResource(R.drawable.baby_btn_selected);
                btn_girl.setBackgroundColor(Color.TRANSPARENT);
                break;

            case R.id.btn_girl:
                btn_boy.setBackgroundColor(Color.TRANSPARENT);
                btn_girl.setBackgroundResource(R.drawable.baby_btn_selected);
                break;
        }
    }


}
