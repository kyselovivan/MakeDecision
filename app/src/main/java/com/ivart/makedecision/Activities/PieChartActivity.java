package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
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
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

public class PieChartActivity extends Activity {

    private float[] yData;
    ArrayList<String> questions;
    ArrayList<String> resultQuestion;
    FrameLayout mainActivity;
    Realm realm;
    PieChart mChart;
    String decisionName;
    TextView btnYesOrNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        btnYesOrNo = (TextView)findViewById(R.id.btn_yesOrNo);
        Intent intent = getIntent();
        double[] results = intent.getDoubleArrayExtra("results");
        Long id = intent.getLongExtra("decisionId",0);
        decisionName = getDecisionName(id);
        yData = new float[results.length];
        for (int i = 0; i < results.length; i++) {
            yData[i] = (float) results[i];
        }
        mainActivity = (FrameLayout) findViewById(R.id.activity_pie_chart);
        mChart = (PieChart) findViewById(R.id.myPieChart);

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
                    Toast.makeText(PieChartActivity.this," "+questions.get((int)e.getData()),Toast.LENGTH_SHORT).show();
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
        colors.add(Color.parseColor("#ffff8d"));//yellow
        colors.add(Color.parseColor("#03a9f4"));//blue

        questions.add(getBaseContext().getString(R.string.what_will_if_it_happens));
        questions.add(getBaseContext().getString(R.string.what_will_if_it_doesnt_happen));
        questions.add(getBaseContext().getString(R.string.what_wont_be_if_it_happens));
        questions.add(getBaseContext().getString(R.string.what_wont_be_if_id_doesnt_happens));


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

        PieDataSet dataSet = new PieDataSet(yVals, "Your Decision");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(4);


        dataSet.setColors(resultColor);

        PieData pieData = new PieData(dataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(Color.parseColor("#212121"));
//        pieData.setValueTypeface(Typeface.SANS_SERIF);

        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    public String getDecisionName(final long id){
        final StringBuilder name = new StringBuilder();
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Decision result = realm.where(Decision.class).equalTo("id", id).findFirst();
                name.append(result.getmDecisionName());
            }
        });
        return name.toString();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_pie_chart);
        Intent intent = getIntent();
        double[] results = intent.getDoubleArrayExtra("results");
        Long id = intent.getLongExtra("decisionId",0);
        decisionName = getDecisionName(id);
        yData = new float[results.length];
        for (int i = 0; i < results.length; i++) {
            yData[i] = (float) results[i];
        }
        mainActivity = (FrameLayout) findViewById(R.id.activity_pie_chart);
        mChart = (PieChart) findViewById(R.id.myPieChart);

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
    }

    public void onClick(View view) {
        if (btnYesOrNo != null) {
            btnYesOrNo.setEnabled(false);
        }

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        btnYesOrNo.setEnabled(true);
                        Intent intent = new Intent(PieChartActivity.this, YesOrNoActivity.class);
                        intent.putExtra("resultArray", yData);
                        startActivity(intent);
                    }
                });
            }
        }, 1000);
    }
}