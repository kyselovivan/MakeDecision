package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ivart.makedecision.R;

import java.util.ArrayList;

public class PieChartActivity extends Activity {

    private float[] yData;

    FrameLayout mainActivity;
    PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        Intent intent = getIntent();
        double[] results = intent.getDoubleArrayExtra("results");
        yData = new float[results.length];
        for(int i=0;i<results.length;i++){
            yData[i] = (float)results[i];
        }
        mainActivity = (FrameLayout) findViewById(R.id.activity_pie_chart);
        mChart = (PieChart) findViewById(R.id.myPieChart);

        mChart.setUsePercentValues(true);
        mChart.setDescription("Your Decision");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(5);
        mChart.setTransparentCircleRadius(7);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.animateX(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setBackgroundColor(Color.LTGRAY);

        addData();
    }

    private void addData() {
        ArrayList<PieEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < yData.length; i++) {
            if(yData[i]!= 0) {
                yVals1.add(new PieEntry(yData[i], i));
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<String> questions = new ArrayList<>();
        questions.add("What will be if it happens");
        questions.add("What will be if it doesn't");
        questions.add("What won't be it it happens");
        questions.add("What won't be if it doesn't happen");

        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);


        Legend l = mChart.getLegend();
        l.setCustom(colors,questions);
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setTextSize(10.5F);

        PieDataSet dataSet = new PieDataSet(yVals1,"Your Decision");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(4);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(Color.BLACK);
        pieData.setValueTypeface(Typeface.SANS_SERIF);

        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();
    }
}
