package com.example.dsm2016.baby_book.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.dsm2016.baby_book.R;

/**
 * Created by ghdth on 2018-04-25.
 */

public class Dialog_Diaries_Title extends Dialog {
    private Button save;
    private EditText title_et;
    public Dialog_Diaries_Title(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.dialog_diaries_title);

        title_et=(EditText)findViewById(R.id.dialog_title_et);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=title_et.getText().toString();
                dismiss();
            }
        });
    }
}
