package com.example.dsm2016.baby_book;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SelectMusic extends AppCompatActivity {

    RadioButton btn_music1, btn_music2, btn_music3, btn_music4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);

        btn_music1 = (RadioButton)findViewById(R.id.btn_music1);
        btn_music2 = (RadioButton)findViewById(R.id.btn_music2);
        btn_music3 = (RadioButton)findViewById(R.id.btn_music3);
        btn_music4 = (RadioButton)findViewById(R.id.btn_music4);

        final SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        final int music1 = soundPool.load(this,R.raw.sway, 1);
        final int music2 = soundPool.load(this, R.raw.been_meaning_to_tell_you, 1);
        final int music3 = soundPool.load(this, R.raw.feel_it_still, 1);
        final int music4 = soundPool.load(this, R.raw.fade_away, 1);

        btn_music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(music1, 1, 1, 0, 0, 1);
            }
        });

        btn_music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(music2, 1, 1, 0, 0, 1);
            }
        });

        btn_music3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(music3, 1, 1, 0, 0, 1);
            }
        });

        btn_music4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(music4, 1, 1, 0, 0, 1);
            }
        });
    }
}
