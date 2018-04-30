package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.dsm2016.baby_book.Dialog.Dialog_Diaries_Title;
import com.example.dsm2016.baby_book.Item.Item_Mydairies;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DiariesActivity extends BaseActivity  {

    private ImageView preview;
    private Button btn_calendar;
    private FloatingActionButton plus;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item_Mydairies> item_mydairies;
    Item_Mydairies item;
    Dialog_Diaries_Title dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaries);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        dialog=new Dialog_Diaries_Title(this);
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(dialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        wm.width = width / 2;  //화면 너비의 절반
        wm.height = height / 3;  //화면 높이의 절반
        plus=(FloatingActionButton)findViewById(R.id.float_add_album);
        plus.setIcon(R.drawable.ic_add);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"버튼",Toast.LENGTH_LONG).show();
                dialog.show();
            }
        });
        btn_calendar = (Button)findViewById(R.id.btn_calendar);

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
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

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(),recyclerView,new RecyclerViewClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                preview=(ImageView)findViewById(R.id.preview);
                preview.setImageResource(item_mydairies.get(position).getImage());

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }


}
