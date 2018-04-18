package com.example.dsm2016.baby_book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ghdth on 2018-04-17.
 */

public class SignupActivity extends BaseActivity {
    EditText ed_id, ed_pwd,ed_pwd_comfrim,ed_email,ed_name,ed_code;
    Button signup;
    private RadioGroup radioGroup;
    private int sex=0;
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
                signup();
            }
        });



    }
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i==R.id.mom_radio){
                sex=1;
            }
            else if(i==R.id.father_radio){
                sex=2;
            }
        }
    };
    public void signup(){
        ed_id=(EditText)findViewById(R.id.signup_id);
        ed_pwd=(EditText)findViewById(R.id.signup_pwd);
        ed_pwd_comfrim=(EditText)findViewById(R.id.signup_pwd_comfrim);
        ed_email=(EditText)findViewById(R.id.signup_email);
        ed_name=(EditText)findViewById(R.id.signup_name);
        ed_code=(EditText)findViewById(R.id.signup_code);

        String id=ed_id.getText().toString();
        String pwd=ed_pwd.getText().toString();
        String pwd_comfrim=ed_pwd_comfrim.getText().toString();
        String email=ed_email.getText().toString();
        String name=ed_email.getText().toString();
        String code=ed_code.getText().toString();

        if(id.equals("")||pwd.equals("")||pwd_comfrim.equals("")||email.equals("")||name.equals("")||sex==0){
            Toast.makeText(getApplicationContext(),"꼭 입력해 주세요.",Toast.LENGTH_LONG).show();
        }
        else if(id.equals(" ")||pwd.equals(" ")||pwd_comfrim.equals(" ")||email.equals(" ")||name.equals(" ")||code.equals(" ")){
            Toast.makeText(getApplicationContext(),"공백 ㄴㄴ",Toast.LENGTH_LONG).show();
        }
        else if(!id.isEmpty()&&!pwd.isEmpty()&&!pwd_comfrim.isEmpty()&&!email.isEmpty()&&!name.isEmpty()&&sex!=0){
            if(pwd.equals(pwd_comfrim)){
                if(!code.isEmpty()){
                    //코드가 입렵되었을 때
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    //코드가 입력되지 않았을 때
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"비밀번호 안 맞음",Toast.LENGTH_LONG).show();

            }
        }

    }
}
