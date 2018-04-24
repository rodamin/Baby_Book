package com.example.dsm2016.baby_book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dsm2016.baby_book.Sever.APIinterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ghdth on 2018-04-17.
 */

public class SignupActivity extends BaseActivity {
    private Retrofit retrofit;
    private APIinterface apIinterface;

    EditText ed_id, ed_pwd,ed_pwd_comfirm,ed_email,ed_name,ed_code;
    Button signup;

    private RadioGroup radioGroup;
    private int gender=2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button)findViewById(R.id.btn_signup);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("로그인","로그인");
                signup_btn();
            }
        });



    }
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i==R.id.mom_radio){
                gender=0;
            }
            else if(i==R.id.father_radio){
                gender=1;
            }
        }
    };
    public void signup_btn(){
        Log.d("하하","하하");
        ed_id=(EditText)findViewById(R.id.signup_id);
        ed_pwd=(EditText)findViewById(R.id.signup_pwd);
        ed_pwd_comfirm=(EditText)findViewById(R.id.signup_pwd_comfrim);
        ed_email=(EditText)findViewById(R.id.signup_email);
        ed_name=(EditText)findViewById(R.id.signup_name);
        ed_code=(EditText)findViewById(R.id.signup_code);

        String id=ed_id.getText().toString();
        String pwd=ed_pwd.getText().toString();
        String pwd_comfirm=ed_pwd_comfirm.getText().toString();
        String email=ed_email.getText().toString();
        String name=ed_email.getText().toString();
        String code=ed_code.getText().toString();

        if(id.equals("")||pwd.equals("")||pwd_comfirm.equals("")||email.equals("")||name.equals("")||gender==2){
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        }
        else if(id.equals(" ")||pwd.equals(" ")||pwd_comfirm.equals(" ")||email.equals(" ")||name.equals(" ")||code.equals(" ")){
            Toast.makeText(getApplicationContext(),"공백 ㄴㄴ",Toast.LENGTH_LONG).show();
        }
        else if(!id.isEmpty()&&!pwd.isEmpty()&&!pwd_comfirm.isEmpty()&&!email.isEmpty()&&!name.isEmpty()&&gender!=2){
            if(pwd.equals(pwd_comfirm)){
                if(!code.isEmpty()){
                   retrofit_conjoin(id,pwd,name,email,gender,code);

                    /*Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();*/

                }
                else{
                    //코드가 입력되지 않았을 때
                     retrofit_join(id,pwd,name,email,gender);
                     Log.d("코드","x");
                    /*Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();*/
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"비밀번호 안 맞음",Toast.LENGTH_LONG).show();

            }
        }



    }

    //첫사용자 회원가입
    public void retrofit_join(String id, String password,String name, String email, int gender ){
        Log.d("첫","사용자");
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.join(id,password,name,email,gender);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){

                    Log.d("회원가입","성공");
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"회원가입 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Log.d("회원가입","실패");
                    Toast.makeText(getApplicationContext(),"회원가입 실패",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());
            }
        });

    }
    //두 번째 사용자
    public void retrofit_conjoin(String id, String password,String name, String email,int gender, String other_id){
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.conjoin(id,password,name,email,gender, other_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("두번째 사용자 회원가입","성공");
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"두 번째 사용자 회원가입 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Log.d("회원가입","실패");
                    Toast.makeText(getApplicationContext(),"회원가입 실패",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());

            }
        });
    }

}
