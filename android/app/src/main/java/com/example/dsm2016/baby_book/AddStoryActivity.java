package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AddStoryActivity extends BaseActivity {

    private LinearLayout linear_couple, linear_marry, linear_pregnant, linear_born;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        linear_couple = (LinearLayout)findViewById(R.id.linear_couple);
        linear_marry = (LinearLayout)findViewById(R.id.linear_marry);
        linear_pregnant = (LinearLayout)findViewById(R.id.linear_pregnant);
        linear_born = (LinearLayout)findViewById(R.id.linear_born);

        linear_couple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CoupleStoryActivity.class);
                startActivity(intent);
            }
        });

        linear_marry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarryStoryActivity.class);
                startActivity(intent);
            }
        });

        linear_pregnant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PregnantStoryActivity.class);
                startActivity(intent);
            }
        });

        linear_born.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BabyStoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
