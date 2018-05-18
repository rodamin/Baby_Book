package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Sever.APIinterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GrowthInfoActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private APIinterface apIinterface;
    Realm mRealm;
    DB_Code db_qna;

    EditText edit_height, edit_weight;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_info);

        edit_height = (EditText)findViewById(R.id.edit_height);
        edit_weight = (EditText)findViewById(R.id.edit_weight);
        btn_save = (Button)findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String baby_name = "JeongGeunCheol";
                String date = getNowDate();
                int height = Integer.parseInt(edit_height.getText().toString());
                int weight = Integer.parseInt(edit_weight.getText().toString());

                mRealm = Realm.getDefaultInstance();
                RealmResults<DB_Code> results = mRealm.where(DB_Code.class).findAll();

                for(int i = 0; i < results.size(); i++){
                    db_qna = results.get(i);
                    Log.d("db_qna", "protocol : " + db_qna);
                }

                int code = db_qna.getCode();
                Log.d("getCode", code+"");

                // retrofit
                retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
                apIinterface=retrofit.create(APIinterface.class);
                Call<Void> call=apIinterface.growth_store(baby_name, date, weight, height, code);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        int status=response.code();
                        if(status==201){
                            Log.d("성장정보 전달","성공");

                            Intent intent=new Intent(getApplicationContext(), MyDiaryActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),"성장정보 저장 성공",Toast.LENGTH_LONG).show();
                        }
                        else if(status==404){
                            Toast.makeText(getApplicationContext(),"성장정보 저장 실패",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("연결","실패"+t.getMessage());
                    }
                });
            }
        });
    }

    public String getNowDate() {
        SimpleDateFormat formatter
                = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);

        return currentDate;
    }

}
