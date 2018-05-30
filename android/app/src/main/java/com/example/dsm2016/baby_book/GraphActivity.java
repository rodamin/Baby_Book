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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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

    Realm mRealm;
    DB_Code db_qna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // chart_height
        // x축 라벨 추가
        ArrayList<String> dates = new ArrayList<String>();
        dates.add("5/22");
        dates.add("6/22");
        dates.add("7/22");
        dates.add("8/22");

        // 표시할 데이터 추가
        ArrayList<Entry> heights = new ArrayList<>();
        heights.add(new Entry(140, 0));
        heights.add(new Entry(142, 1));
        heights.add(new Entry(142, 2));
        heights.add(new Entry(144, 3));

        // Dataset 설정
        LineChart chart_height = (LineChart)findViewById(R.id.chart_height);

        LineDataSet lineDataSet_h = new LineDataSet(heights, "");
        lineDataSet_h.setColors(ColorTemplate.VORDIPLOM_COLORS);
        lineDataSet_h.setDrawCircles(true);
        lineDataSet_h.setDrawFilled(false);        // 선 아래로 색상 표시
        lineDataSet_h.setDrawValues(false);
        lineDataSet_h.setLineWidth(3);
        lineDataSet_h.setCircleSize(5);
        lineDataSet_h.setCircleColor(Color.parseColor("#f24141"));
        lineDataSet_h.setColor(Color.parseColor("#f24141"));
        lineDataSet_h.setDrawCircleHole(true);
        lineDataSet_h.setDrawCircles(true);

        XAxis xAxis_h = chart_height.getXAxis();
        xAxis_h.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis_h.setTextColor(Color.BLACK);

        YAxis LyAxis_h = chart_height.getAxisLeft();
        LyAxis_h.setTextColor(Color.BLACK);

        YAxis RyAxis_h = chart_height.getAxisRight();
        RyAxis_h.setDrawLabels(false);
        RyAxis_h.setDrawAxisLine(false);
        RyAxis_h.setDrawGridLines(false);

        chart_height.getAxisLeft().setStartAtZero(false);

        LineData lineData_h = new LineData(dates, lineDataSet_h);
        chart_height.setData(lineData_h);

        chart_height.animateY(2000, Easing.EasingOption.EaseInCubic);
        chart_height.invalidate();

        //chart_weight
        // 표시할 데이터 추가
        ArrayList<Entry> weights = new ArrayList<>();
        weights.add(new Entry(50, 0));
        weights.add(new Entry(52, 1));
        weights.add(new Entry(52, 2));
        weights.add(new Entry(54, 3));

        // Dataset 설정
        LineChart chart_weight = (LineChart)findViewById(R.id.chart_weight);

        LineDataSet lineDataSet_w = new LineDataSet(weights, "");
        lineDataSet_w.setColors(ColorTemplate.VORDIPLOM_COLORS);
        lineDataSet_w.setDrawCircles(true);
        lineDataSet_w.setDrawFilled(false);        // 선 아래로 색상 표시
        lineDataSet_w.setDrawValues(false);
        lineDataSet_w.setLineWidth(3);
        lineDataSet_w.setCircleSize(5);
        lineDataSet_w.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_w.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_w.setDrawCircleHole(true);
        lineDataSet_w.setDrawCircles(true);

        XAxis xAxis_w = chart_weight.getXAxis();
        xAxis_w.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis_w.setTextColor(Color.BLACK);

        YAxis LyAxis_w = chart_weight.getAxisLeft();
        LyAxis_w.setTextColor(Color.BLACK);

        YAxis RyAxis_w = chart_weight.getAxisRight();
        RyAxis_w.setDrawLabels(false);
        RyAxis_w.setDrawAxisLine(false);
        RyAxis_w.setDrawGridLines(false);

        chart_weight.getAxisLeft().setStartAtZero(false);

        LineData lineData_w = new LineData(dates, lineDataSet_w);
        chart_weight.setData(lineData_w);

        chart_weight.animateY(2000, Easing.EasingOption.EaseInCubic);
        chart_weight.invalidate();

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
//        String baby_name = "ParkHaeBin";
//
//        retrofit=new Retrofit.Builder().baseUrl(APIinterface.URL).addConverterFactory(GsonConverterFactory.create()).build();
//        apIinterface = retrofit.create(APIinterface.class);
//
//        Call<JsonArray> call=apIinterface.growth_graph(baby_name, code);
//        call.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                int status=response.code();
//                if(status==201){
//                    Log.d("graph 전달","성공");
//                    Log.d("Response: ", response.body().toString());
//                    Toast.makeText(getApplicationContext(),"성장정보 불러오기 성공",Toast.LENGTH_LONG).show();
//
//                    JsonArray jsonArray=response.body();
//                    Log.d("array 결과", "onResponse: " + jsonArray.toString());
//
//
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
