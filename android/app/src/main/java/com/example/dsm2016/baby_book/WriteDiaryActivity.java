package com.example.dsm2016.baby_book;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.dsm2016.baby_book.Adapter.Adapter_Write_Diary;
import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Item.Item_Write_diary_Image;
import com.example.dsm2016.baby_book.Item.Item_write_diary;
import com.example.dsm2016.baby_book.Sever.APIinterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteDiaryActivity extends BaseActivity {

    private Realm mRealm;
    private Button btn_add_picture,save_btn;
    private EditText title_et,content_et;
    LayoutInflater mInflater;
    LinearLayout mRootLinear;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Item_Write_diary_Image> mitem=new ArrayList<>();
    private ArrayList<Uri> image_item=new ArrayList<>();
    private RecyclerView.Adapter madapter;
    final int REQ_CODE_SELECT_IMAGE=100;
    final int REQ_CODE_SELECT_Camera=101;
    private Uri uri;
    private Retrofit retrofit;
    private APIinterface apIinterface;
    ImageView img;
    int temp = 1;
    String filepath;
    String filename;
    ArrayList<String> arrayList;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);
        title_et=(EditText)findViewById(R.id.write_title);
        content_et=(EditText)findViewById(R.id.write_content);
        btn_add_picture = (Button)findViewById(R.id.btn_add_picture);
        save_btn=(Button)findViewById(R.id.btn_save);

        mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        mRootLinear = (LinearLayout)findViewById(R.id.viewgroup_add_picture);
        img= (ImageView)mInflater.inflate(R.layout.item_add_picture, mRootLinear, false);
        btn_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(WriteDiaryActivity.this);
                alertDialogBuilder.setTitle("사진 가져오기")
                        .setPositiveButton("갤러리", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Gallery();
                                Log.d("1","1");
                            }
                        })
                        .setNeutralButton("카메라", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.write_diary_rv);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        madapter=new Adapter_Write_Diary(mitem,getApplicationContext());
        recyclerView.setAdapter(madapter);
//        madapter.notifyDataSetChanged();
      /*  mitem.add(new Item_write_diary(R.drawable.test2));
        mitem.add(new Item_write_diary(R.drawable.test3));
        mitem.add(new Item_write_diary(R.drawable.test));
        mitem.add(new Item_write_diary(R.drawable.test3));*/

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
    }
    public void Save(){
        String title=title_et.getText().toString();
        String content=content_et.getText().toString();
        Date date=getTime();
        int code=code();
        String baby_name;




    }

    public void Gallery(){
        Log.d("2","2");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dfdf","dfdf");
        if(resultCode==RESULT_OK){
            Log.d("dfdfddddddf","dfdfd");
            switch (requestCode){
                case REQ_CODE_SELECT_IMAGE:
                    uri=data.getData();
                    mitem.add(new Item_Write_diary_Image(uri));
                    filepath=getPath(uri);
                   // filename=filepath.substring(filepath.lastIndexOf("/")+1);
                    arrayList=new ArrayList<>();
                    arrayList.add(filepath);
                    Log.d("사진가져오기", String.valueOf(uri));
                    //Log.d("getPath:",filepath+"filename"+filename);
                    madapter.notifyDataSetChanged();
                    break;
                case REQ_CODE_SELECT_Camera:
                    break;
                default:
                    break;
            }
        }


    }
    private String getPath(Uri uri){
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(),uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public Date getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mDate;


    }
    public int code(){
        int code=0;
        try{
            mRealm = Realm.getDefaultInstance();
        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
        DB_Code db_code=mRealm.where(DB_Code.class).findFirst();
        code=db_code.getCode();
        return  code;
    }
    public void retorofit_save(String title,String content,Date date,int code,String baby_name){

        HashMap<String, RequestBody> map=new HashMap<>(arrayList.size());
        RequestBody file=null;
        for(int i=0;i<arrayList.size();i++){
            file= RequestBody.create(MediaType.parse("image/jpg"),arrayList.get(i));
            map.put(("file\"; filename=\"" + "photoname" + i + ".jpg"), file);

        }
        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).build();
        Call<Void> call=apIinterface.save(baby_name,date,title,content,code,map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int stuatus=response.code();
                if(stuatus==201){
                    Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_LONG).show();
                    finish();
                }
                else if(stuatus==404){
                    Toast.makeText(getApplicationContext(),"서버 에러",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG).show();
                Log.d("연결 실패",t.getMessage());
            }
        });


    }


}
