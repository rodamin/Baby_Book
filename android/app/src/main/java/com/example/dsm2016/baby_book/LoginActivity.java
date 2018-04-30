package com.example.dsm2016.baby_book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsm2016.baby_book.Sever.APIinterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends BaseActivity{
    EditText id_et;
    EditText pwd_et;
    TextView find_id,find_pwd,signup;
    CheckBox cb_autolog;

    private Retrofit retrofit;
    private APIinterface apIinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cb_autolog=(CheckBox)findViewById(R.id.checkbox_autolog);
        find_id=(TextView)findViewById(R.id.text_find_id);
        find_pwd=(TextView)findViewById(R.id.text_find_pw);
        signup=(TextView)findViewById(R.id.signup_tv);
        Button btn_login=(Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

        find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        find_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
    public void login(){
        id_et=(EditText)findViewById(R.id.edit_id);
        pwd_et=(EditText)findViewById(R.id.edit_pw);
        String id=id_et.getText().toString();
        String pwd=pwd_et.getText().toString();
        if(id.equals("")||pwd.equals("")){
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        }
        else if(id.equals(" ")||pwd.equals(" ")){
            Toast.makeText(getApplicationContext(),"공백 포함 ㄴㄴ ",Toast.LENGTH_LONG).show();
        }
        else{
            if (!id.isEmpty() && !pwd.isEmpty()) {
                //레트로핏 로그인
               /* Intent intent=new Intent(getApplicationContext(),DiariesActivity.class);
                startActivity(intent);
                finish();*/
               retrofit_login(id,pwd);
            }
        }
    }
    public void retrofit_login(String id, String password){
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.login(id,password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("로그인","성공");
                    Intent intent=new Intent(getApplicationContext(),DiariesActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"연결 실패",Toast.LENGTH_LONG).show();

            }
        });

    }


}