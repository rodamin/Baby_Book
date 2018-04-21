package com.example.dsm2016.baby_book;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class GraphActivity extends BaseActivity {

    private LineChart chart_height, chart_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        chart_height = (LineChart)findViewById(R.id.chart_height);
        chart_weight = (LineChart)findViewById(R.id.chart_weight);


        // chart_height 구현
        List<Entry> entries_height = new ArrayList<>();
        entries_height.add(new Entry(1, 1));
        entries_height.add(new Entry(2, 2));
        entries_height.add(new Entry(3, 0));
        entries_height.add(new Entry(4, 4));
        entries_height.add(new Entry(5, 3));

        LineDataSet lineDataSetHeight = new LineDataSet(entries_height, "키");
        lineDataSetHeight.setLineWidth(2);
        lineDataSetHeight.setCircleRadius(6);
        lineDataSetHeight.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSetHeight.setCircleColorHole(Color.BLUE);
        lineDataSetHeight.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSetHeight.setDrawCircleHole(true);
        lineDataSetHeight.setDrawCircles(true);
        lineDataSetHeight.setDrawHorizontalHighlightIndicator(false);
        lineDataSetHeight.setDrawHighlightIndicators(false);
        lineDataSetHeight.setDrawValues(false);

        LineData lineDataHeight = new LineData(lineDataSetHeight);
        chart_height.setData(lineDataHeight);

        XAxis xAxis = chart_height.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = chart_height.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = chart_height.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        chart_height.setDoubleTapToZoomEnabled(false);
        chart_height.setDrawGridBackground(false);
        chart_height.setDescription(description);
        chart_height.animateY(2000, Easing.EasingOption.EaseInCubic);
        chart_height.invalidate();


        // chart_weight 구현
        List<Entry> entries_weight = new ArrayList<>();
        entries_weight.add(new Entry(1, 1));
        entries_weight.add(new Entry(2, 2));
        entries_weight.add(new Entry(3, 0));
        entries_weight.add(new Entry(4, 4));
        entries_weight.add(new Entry(5, 3));

        LineDataSet lineDataSetWeight = new LineDataSet(entries_weight, "몸무게");
        lineDataSetWeight.setLineWidth(2);
        lineDataSetWeight.setCircleRadius(6);
        lineDataSetWeight.setCircleColor(Color.parseColor("#F44842"));
        lineDataSetWeight.setCircleColorHole(Color.RED);
        lineDataSetWeight.setColor(Color.parseColor("#F44842"));
        lineDataSetWeight.setDrawCircleHole(true);
        lineDataSetWeight.setDrawCircles(true);
        lineDataSetWeight.setDrawHorizontalHighlightIndicator(false);
        lineDataSetWeight.setDrawHighlightIndicators(false);
        lineDataSetWeight.setDrawValues(false);

        LineData lineDataWeight = new LineData(lineDataSetWeight);
        chart_weight.setData(lineDataWeight);

        XAxis xAxis2 = chart_weight.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setTextColor(Color.BLACK);
        xAxis2.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis2 = chart_weight.getAxisLeft();
        yLAxis2.setTextColor(Color.BLACK);

        YAxis yRAxis2 = chart_weight.getAxisRight();
        yRAxis2.setDrawLabels(false);
        yRAxis2.setDrawAxisLine(false);
        yRAxis2.setDrawGridLines(false);

        Description description2 = new Description();
        description2.setText("");

        chart_weight.setDoubleTapToZoomEnabled(false);
        chart_weight.setDrawGridBackground(false);
        chart_weight.setDescription(description2);
        chart_weight.animateY(2000, Easing.EasingOption.EaseInCubic);
        chart_weight.invalidate();
    }
}
