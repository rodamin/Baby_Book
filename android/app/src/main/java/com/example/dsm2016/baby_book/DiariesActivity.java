package com.example.dsm2016.baby_book;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dsm2016.baby_book.Adapter.Adapter_Mydiaries;
import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Dialog.Dialog_Diaries_Title;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;
import com.example.dsm2016.baby_book.Sever.APIinterface;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.data.DataSet;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.nio.file.Files.delete;

public class DiariesActivity extends BaseActivity  {

    private ImageView preview;
    private Button btn_calendar;
    private FloatingActionButton plus;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item_Mydairies> item_mydairies;
    private Adapter_Mydiaries adapter_mydiaries;
    Item_Mydairies item;
    Dialog_Diaries_Title dialog;

    private Retrofit retrofit;
    private APIinterface apIinterface;
    Realm mRealm;
    DB_Code db_qna;

    private String new_baby_name;
    private Object value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaries);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        dialog=new Dialog_Diaries_Title(this);
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(dialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        wm.width = 800 ;  //화면 너비의 절반
        wm.height = 700;  //화면 높이의 절반
        plus=(FloatingActionButton)findViewById(R.id.float_add_album);
        plus.setIcon(R.drawable.ic_add);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"버튼",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), BabyInfoActivity.class);
                startActivity(intent);
//                dialog.show();
            }
        });
        btn_calendar = (Button)findViewById(R.id.btn_calendar);

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BabyInfoActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.diaries_rv);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        item_mydairies=new ArrayList<>();
        madapter=new Adapter_Mydiaries(item_mydairies,getApplicationContext());
        recyclerView.setAdapter(madapter);

        item_mydairies.add(new Item_Mydairies(R.drawable.background_main));
        item_mydairies.add(new Item_Mydairies(R.drawable.test));
        item_mydairies.add(new Item_Mydairies(R.drawable.test2));
        item_mydairies.add(new Item_Mydairies(R.drawable.test3));

       //Intent intent=getIntent();
        //int image=intent.getIntExtra("image",0);
        //Log.d("dfdfdf",Integer.toString(image));
        //preview=(ImageView)findViewById(R.id.preview);
        //preview.setImageResource(item_mydairies.get(image).getImage());
        adapter_mydiaries=new Adapter_Mydiaries(item_mydairies,getApplicationContext());
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(),recyclerView,new RecyclerViewClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                preview=(ImageView)findViewById(R.id.preview);
                preview.setImageResource(item_mydairies.get(position).getImage());

            }

            @Override
            public void onLongItemClick(View view, final int position) {
                Toast.makeText(getApplicationContext(),"롱클릭"+position,Toast.LENGTH_LONG).show();
                    //삭제부분
                AlertDialog.Builder builder = new AlertDialog.Builder(DiariesActivity.this);
                builder.setTitle("AlertDialog Title");
                builder.setMessage("AlertDialog Content");
                builder.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"삭제 합니다.",Toast.LENGTH_LONG).show();
                               // adapter_mydiaries.deleteItem(position);
                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"취소합니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
            }
        }));

        // 모든 앨범(아기이름) 불러오는 retrofit
        mRealm = Realm.getDefaultInstance();
        RealmResults<DB_Code> results = mRealm.where(DB_Code.class).findAll();

        for(int i = 0; i < results.size(); i++){
            db_qna = results.get(i);
            Log.d("db_qna", "protocol : " + db_qna);
        }

        int code = db_qna.getCode();
        Log.d("getCode", code+"");

        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).addConverterFactory(GsonConverterFactory.create()).build();
        apIinterface=retrofit.create(APIinterface.class);
        Call<JsonArray> call=apIinterface.baby_call(code);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                int status=response.code();
                if(status==201){
                    Log.d("baby_call 통신","성공");
                    Log.d("onResponse: ", response.body().toString());

                    Toast.makeText(getApplicationContext(),"아기들 불러오기 성공",Toast.LENGTH_LONG).show();
                }
                else if(status==404){
                    Toast.makeText(getApplicationContext(),"아기들 불러오기 실패",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("연결","실패"+t.getMessage());
            }
        });
    }


}
