package com.example.dsm2016.baby_book;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dsm2016.baby_book.DB.DB_Code;
import com.example.dsm2016.baby_book.Sever.APIinterface;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GraphActivity extends BaseActivity {

    private Retrofit retrofit;
    private APIinterface apIinterface;
    private LineChart chart_height, chart_weight;

    Realm mRealm;
    DB_Code db_qna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        chart_height = (LineChart)findViewById(R.id.chart_height);
        chart_weight = (LineChart)findViewById(R.id.chart_weight);

//        mRealm = Realm.getDefaultInstance();
//        RealmResults<DB_Code> results = mRealm.where(DB_Code.class).findAll();
//
//        for(int i = 0; i < results.size(); i++){
//            db_qna = results.get(i);
//            Log.d("db_qna", "protocol : " + db_qna);
//        }
//
//        int code = db_qna.getCode();
//        Log.d("getCode", code+"");
//
//        String baby_name = "JeongGeunCheol";

        // retrofit
//        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).addConverterFactory(GsonConverterFactory.create()).build();
//        apIinterface = retrofit.create(APIinterface.class);
//
//        Call<JsonArray> call=apIinterface.growth_graph(baby_name, code);
//        call.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                int status=response.code();
//                if(status==201){
//                    Log.d("baby_info 전달","성공");
//                    Toast.makeText(getApplicationContext(),"성장정보 불러오기 성공",Toast.LENGTH_LONG).show();
//
//                    JsonArray jsonArray=response.body();
//                    Log.d("array 결과", "onResponse: " + jsonArray.toString());
//                }
//                else if(status==404){
//                    Toast.makeText(getApplicationContext(),"성장정보 불러오기 실패",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//                Log.d("연결","실패"+t.getMessage());
//            }
//        });

    }
}

//                    List<Entry> entries_height = new ArrayList<>();
//                    List<Entry> entries_weight = new ArrayList<>();
//
//                    for(int i = 0; i < jsonArray.size(); i++) {
//                        JsonObject object = jsonArray.get(i).getAsJsonObject();
//                        Log.d("object", "onResponse: " + object.get("height"));
//
//                        entries_height.add(new Entry(1, jsonArray.get(i).getAsJsonObject().get("height").getAsInt()));
//                        entries_weight.add(new Entry(1, jsonArray.get(i).getAsJsonObject().get("weight").getAsInt()));
//
//                        //char_height
//                        LineDataSet lineDataSetHeight = new LineDataSet(entries_height, "키");
//                        lineDataSetHeight.setLineWidth(2);
//                        lineDataSetHeight.setCircleRadius(6);
//                        lineDataSetHeight.setCircleColor(Color.parseColor("#FFA1B4DC"));
//                        lineDataSetHeight.setCircleColorHole(Color.BLUE);
//                        lineDataSetHeight.setColor(Color.parseColor("#FFA1B4DC"));
//                        lineDataSetHeight.setDrawCircleHole(true);
//                        lineDataSetHeight.setDrawCircles(true);
//                        lineDataSetHeight.setDrawHorizontalHighlightIndicator(false);
//                        lineDataSetHeight.setDrawHighlightIndicators(false);
//                        lineDataSetHeight.setDrawValues(false);
//
//                        LineData lineDataHeight = new LineData(lineDataSetHeight);
//                        chart_height.setData(lineDataHeight);
//
//                        XAxis xAxis = chart_height.getXAxis();
//                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                        xAxis.setTextColor(Color.BLACK);
//                        xAxis.enableGridDashedLine(8, 24, 0);
//
//                        YAxis yLAxis = chart_height.getAxisLeft();
//                        yLAxis.setTextColor(Color.BLACK);
//
//                        YAxis yRAxis = chart_height.getAxisRight();
//                        yRAxis.setDrawLabels(false);
//                        yRAxis.setDrawAxisLine(false);
//                        yRAxis.setDrawGridLines(false);
//
//                        Description description = new Description();
//                        description.setText("");
//
//                        chart_height.setDoubleTapToZoomEnabled(false);
//                        chart_height.setDrawGridBackground(false);
//                        chart_height.setDescription(description);
//                        chart_height.animateY(2000, Easing.EasingOption.EaseInCubic);
//                        chart_height.invalidate();
//
//                        //char_weight
//                        LineDataSet lineDataSetWeight = new LineDataSet(entries_weight, "몸무게");
//                        lineDataSetWeight.setLineWidth(2);
//                        lineDataSetWeight.setCircleRadius(6);
//                        lineDataSetWeight.setCircleColor(Color.parseColor("#F44842"));
//                        lineDataSetWeight.setCircleColorHole(Color.RED);
//                        lineDataSetWeight.setColor(Color.parseColor("#F44842"));
//                        lineDataSetWeight.setDrawCircleHole(true);
//                        lineDataSetWeight.setDrawCircles(true);
//                        lineDataSetWeight.setDrawHorizontalHighlightIndicator(false);
//                        lineDataSetWeight.setDrawHighlightIndicators(false);
//                        lineDataSetWeight.setDrawValues(false);
//
//                        LineData lineDataWeight = new LineData(lineDataSetWeight);
//                        chart_weight.setData(lineDataWeight);
//
//                        XAxis xAxis2 = chart_weight.getXAxis();
//                        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
//                        xAxis2.setTextColor(Color.BLACK);
//                        xAxis2.enableGridDashedLine(8, 24, 0);
//
//                        YAxis yLAxis2 = chart_weight.getAxisLeft();
//                        yLAxis2.setTextColor(Color.BLACK);
//
//                        YAxis yRAxis2 = chart_weight.getAxisRight();
//                        yRAxis2.setDrawLabels(false);
//                        yRAxis2.setDrawAxisLine(false);
//                        yRAxis2.setDrawGridLines(false);
//
//                        Description description2 = new Description();
//                        description2.setText("");
//
//                        chart_weight.setDoubleTapToZoomEnabled(false);
//                        chart_weight.setDrawGridBackground(false);
//                        chart_weight.setDescription(description2);
//                        chart_weight.animateY(2000, Easing.EasingOption.EaseInCubic);
//                        chart_weight.invalidate();
//
//                    }