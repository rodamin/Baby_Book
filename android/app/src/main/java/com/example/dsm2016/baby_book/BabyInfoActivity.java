package com.example.dsm2016.baby_book;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dsm2016.baby_book.Sever.APIinterface;

import retrofit2.Call;
import retrofit2.Retrofit;

public class BabyInfoActivity extends BaseActivity implements View.OnClickListener{

    private Retrofit retrofit;
    private APIinterface apIinterface;

    private ImageButton btn_boy, btn_girl;
    private EditText edit_name, edit_age, edit_birth;
    private Button btn_next;
    private int gender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);

        btn_boy = (ImageButton)findViewById(R.id.btn_boy);
        btn_girl = (ImageButton)findViewById(R.id.btn_girl);
        btn_next = (Button)findViewById(R.id.btn_next);

        btn_boy.setOnClickListener(this);
        btn_girl.setOnClickListener(this);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("아기정보: ", "입력");
                next_btn();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_boy:
                gender = 1;
                btn_boy.setBackgroundResource(R.drawable.baby_btn_selected);
                btn_girl.setBackgroundColor(Color.TRANSPARENT);
                break;

            case R.id.btn_girl:
                gender = 0;
                btn_boy.setBackgroundColor(Color.TRANSPARENT);
                btn_girl.setBackgroundResource(R.drawable.baby_btn_selected);
                break;
        }
    }

    public void next_btn() {
        Log.d("next_btn()호출: ", "success");
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_age = (EditText)findViewById(R.id.edit_age);
        edit_birth = (EditText)findViewById(R.id.edit_birth);

        String name = edit_name.getText().toString();
        String age = edit_age.getText().toString();
        String birth = edit_birth.getText().toString();

        if(name.equals("") || age.equals("") || birth.equals("") || gender == 2) {
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        } else if(name.equals(" ") || age.equals(" ") || birth.equals(" ")) {
            Toast.makeText(getApplicationContext(),"공백 금지",Toast.LENGTH_LONG).show();
        } else if(!name.isEmpty() && !age.isEmpty() && !birth.isEmpty() && gender != 2 ) {
            retrofit_babyinfo(name, age, birth, gender);
        }
    }

    public void retrofit_babyinfo(String name, String age, String birth, int gender) {
        Log.d("retrofit_babyinfo", "호출");
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);

    }
}
