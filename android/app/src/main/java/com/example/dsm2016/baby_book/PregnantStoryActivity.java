package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Sever.APIinterface;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PregnantStoryActivity extends BaseActivity {

    private Retrofit retrofit;
    private APIinterface apIinterface;
    Realm mRealm;
    DB_Code db_qna;

    EditText edit_story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant_story);

        edit_story = (EditText)findViewById(R.id.edit_story);

        int idx = 3;
        String story = edit_story.getText().toString();

        mRealm = Realm.getDefaultInstance();
        RealmResults<DB_Code> results=mRealm.where(DB_Code.class).findAll();

        for(int i=0;i<results.size();i++){
            db_qna=results.get(i);
            Log.d("db_qna", "protocol : " + db_qna);
        }

        int code = db_qna.getCode();
        Log.d("xxx", code+"");

        String baby_name = "ParkHaeBin";


        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<Void> call=apIinterface.story_write(baby_name, idx, story, code);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int status=response.code();
                if(status==201){
                    Log.d("couple story 전달","성공");
                    Intent intent=new Intent(getApplicationContext(), AddStoryActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"커플 스토리가 저장되었습니다.",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText(getApplicationContext(),"커플 스토리 저장에 실패하였습니다.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());
            }
        });
    }
}
