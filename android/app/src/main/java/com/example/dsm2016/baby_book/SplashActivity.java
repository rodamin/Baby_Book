package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by ghdth on 2018-04-11.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView imageView=(ImageView)findViewById(R.id.loding);
        final AnimationDrawable frameAnimation=(AnimationDrawable)imageView.getBackground();
        imageView.post(new Runnable() {
            @Override
            public void run() {
               frameAnimation.start();
            }
        });
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
   
}
