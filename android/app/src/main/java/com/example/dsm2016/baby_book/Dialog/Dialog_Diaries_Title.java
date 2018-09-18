package com.example.dsm2016.baby_book.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dsm2016.baby_book.MyDiaryActivity;
import com.example.dsm2016.baby_book.R;

/**
 * Created by ghdth on 2018-04-25.
 */

public class Dialog_Diaries_Title extends Dialog {
    private Button save;
    private ImageView cancel_btn;
    private EditText title_et;
    private Context context;
    public Dialog_Diaries_Title(@NonNull Context context) {
        super(context);
        this.context=context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.dialog_diaries_title);
        Toast.makeText(context,"저장", Toast.LENGTH_LONG).show();
        title_et=(EditText)findViewById(R.id.dialog_title_et);
        save=(Button)findViewById(R.id.dialog_title_btn);
        cancel_btn=(ImageView) findViewById(R.id.dialog_title_cancel) ;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=title_et.getText().toString();
                Intent intent=new Intent(getContext(), MyDiaryActivity.class);
                intent.putExtra("title",title);
                getContext().startActivity(intent);
                dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

    }
}
