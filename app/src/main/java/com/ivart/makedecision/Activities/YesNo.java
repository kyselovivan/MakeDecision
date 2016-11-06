package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ivart.makedecision.R;

import java.util.ArrayList;


/**
 * Created by Artem on 01.11.2016.
 */

public class YesNo extends Activity {
    private float[] yData = {70, 30} ;
    ArrayList<String> questions;
    ArrayList<String> resultQuestion;
    FrameLayout mainActivity;
    PieChart mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no);

        mainActivity = (FrameLayout) findViewById(R.id.activity_pie_chart_yes_no);
        mChart = (PieChart) findViewById(R.id.myPieChart2);

        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setDescriptionTextSize(20);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(5);
        mChart.setTransparentCircleRadius(7);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.animateX(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setBackgroundColor(Color.parseColor("#607d8b"));

        addData();

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if(e==null) return;
                else{
                    Toast.makeText(YesNo.this," "+questions.get((int)e.getData()),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addData() {
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<PieEntry> yVals = new ArrayList<>();
        questions = new ArrayList<>();

        colors.add(Color.parseColor("#54af49"));//green
        colors.add(Color.parseColor("#f08080"));//red

        questions.add("Yes");
        questions.add("No");



        ArrayList<Integer> resultColor = new ArrayList<>();
        resultQuestion = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            if(yData[i]!=0){
                yVals.add(new PieEntry(yData[i], i));
                resultColor.add(colors.get(i));
                resultQuestion.add(questions.get(i));
            }
        }


        Legend l = mChart.getLegend();
        l.setCustom(resultColor, resultQuestion);
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setTextSize(10.5F);

        PieDataSet dataSet = new PieDataSet(yVals, "Make Decision");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(4);


        dataSet.setColors(resultColor);

        PieData pieData = new PieData(dataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(Color.parseColor("#212121"));

        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();
    }


    public void onClick(View view) {
        Intent intent = new Intent(this, YesNo.class);
        startActivity(intent);
    }
}
