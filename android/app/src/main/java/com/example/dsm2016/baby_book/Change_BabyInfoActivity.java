package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Sever.APIinterface;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Change_BabyInfoActivity extends BaseActivity implements View.OnClickListener{

    private Retrofit retrofit;
    private APIinterface apIinterface;
    private Realm mRealm;
    private DB_Code db_qna;

    private ImageButton btn_boy, btn_girl;
    private EditText edit_prev_name, edit_new_name, edit_birth;
    private Button btn_change;
    private int gender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_baby_info);

        btn_boy = (ImageButton)findViewById(R.id.btn_boy);
        btn_girl = (ImageButton)findViewById(R.id.btn_girl);
        btn_change = (Button)findViewById(R.id.btn_change);

        btn_boy.setOnClickListener(this);
        btn_girl.setOnClickListener(this);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick btn_change", "success");
                change_btn();
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

    public void change_btn() {
        Log.d("change_btn()", "호출");

        edit_new_name = (EditText)findViewById(R.id.edit_new_name);
        edit_prev_name = (EditText)findViewById(R.id.edit_prev_name);
        edit_birth = (EditText)findViewById(R.id.edit_birth);

        String new_baby_name = edit_new_name.getText().toString();
        String prev_baby_name = edit_prev_name.getText().toString();
        String birth = edit_birth.getText().toString();

        mRealm = Realm.getDefaultInstance();
        RealmResults<DB_Code> results=mRealm.where(DB_Code.class).findAll();

        for(int i=0;i<results.size();i++){
            db_qna=results.get(i);
            Log.d("db_qna", "protocol : " + db_qna);
        }

        int code = db_qna.getCode();
        Log.d("xxx", code+"");

        if(new_baby_name.equals("") || prev_baby_name.equals("") || birth.equals("") || gender == 2) {
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        } else if(new_baby_name.equals(" ") || prev_baby_name.equals(" ") || birth.equals(" ")) {
            Toast.makeText(getApplicationContext(),"공백 금지",Toast.LENGTH_LONG).show();
        } else if(!new_baby_name.isEmpty() && !prev_baby_name.isEmpty() && gender != 2 ) {
            retrofit_new_babyinfo(new_baby_name, prev_baby_name, gender, birth, code);
        }

    }

    public void retrofit_new_babyinfo(final String new_baby_name, String prev_baby_name, int gender, String birth, int code) {
        Log.d("retrofit_new_babyinfo", "success");
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.baby_update(new_baby_name, prev_baby_name, gender, birth, code);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("new_baby_info 전달",response.body()+"");
                    Intent intent=new Intent(getApplicationContext(),MyDiaryActivity.class);
                    intent.putExtra("new_baby_name", new_baby_name);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"수정 완료",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText(getApplicationContext(),"기존의 아기 이름이 맞지 않습니다.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());

            }
        });
    }
}
