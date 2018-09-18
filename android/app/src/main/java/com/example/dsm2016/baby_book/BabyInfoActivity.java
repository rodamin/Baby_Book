package com.example.dsm2016.baby_book;

import android.content.Intent;
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

import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Sever.APIinterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BabyInfoActivity extends BaseActivity implements View.OnClickListener{

    private Retrofit retrofit;
    private APIinterface apIinterface;
    Realm mRealm;
    DB_Code db_qna;

    private ImageButton btn_boy, btn_girl;
    private EditText edit_name, edit_birth;
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
    public void onBackPressed() {
        Intent intent_diaries = new Intent(getApplicationContext(), DiariesActivity.class);
        startActivity(intent_diaries);
        super.onBackPressed();
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
        edit_birth = (EditText)findViewById(R.id.edit_birth);

        String baby_name = edit_name.getText().toString();
        String birth = edit_birth.getText().toString();

        mRealm = Realm.getDefaultInstance();
        RealmResults<DB_Code> results=mRealm.where(DB_Code.class).findAll();

        for(int i=0;i<results.size();i++){
            db_qna=results.get(i);
            Log.d("db_qna", "protocol : " + db_qna);
        }

        int code = db_qna.getCode();
        Log.d("xxx", code+"");

        if(baby_name.equals("") || birth.equals("") || gender == 2) {
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        } else if(baby_name.equals(" ") || birth.equals(" ")) {
            Toast.makeText(getApplicationContext(),"공백 금지",Toast.LENGTH_LONG).show();
        } else if(!baby_name.isEmpty() && gender != 2 ) {
                retrofit_babyinfo(baby_name, birth, gender, code);
        }
    }

    public void retrofit_babyinfo(final String baby_name, String birth, int gender, int code) {
        Log.d("retrofit_babyinfo", "호출");
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.baby(baby_name, gender, birth, code);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("baby_info 전달","성공");

                    Intent intent=new Intent(getApplicationContext(),MyDiaryActivity.class);
                    intent.putExtra("baby_name", baby_name);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"baby_info 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText(getApplicationContext(),"baby_info 실패",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());
            }
        });
    }
}
