package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ivart.makedecision.Model.CalculateDecison;
import com.ivart.makedecision.R;


public class SquareActivity extends Activity implements View.OnClickListener {
    Long decisionId;
    Button firstSquare;
    Button secondSquare;
    Button thirdSquare;
    Button fourthSquare;
    ImageView calculate_decision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_square);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("decisionId", 0);

        firstSquare = (Button) findViewById(R.id.btn_will_if_it_happens);
        secondSquare = (Button) findViewById(R.id.btn_will_if_it_doesnt);
        thirdSquare = (Button) findViewById(R.id.btn_wont_if_it_happens);
        fourthSquare = (Button) findViewById(R.id.btn_wont_if_it_doesnt);
        calculate_decision = (ImageView) findViewById(R.id.calculate);
        setOnClick();

        Toast.makeText(this, "Decision id : " + decisionId, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_will_if_it_happens:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                }
                startDescriptionActivity(decisionId,1);
                break;
            case R.id.btn_will_if_it_doesnt:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                }
                startDescriptionActivity(decisionId,2);
                break;
            case R.id.btn_wont_if_it_happens:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                }
                startDescriptionActivity(decisionId,3);
                break;
            case R.id.btn_wont_if_it_doesnt:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                }
                startDescriptionActivity(decisionId,4);
                break;
            case R.id.calculate:
                CalculateDecison calculate = new CalculateDecison();
                double ifItHapp = calculate.getRaitingBySquare(decisionId,1);
                double ifItDoesnt = calculate.getRaitingBySquare(decisionId,2);
                double wontItItHapp = calculate.getRaitingBySquare(decisionId,3);
                double wontItItDoesnt = calculate.getRaitingBySquare(decisionId,4);
                double[] results = {ifItHapp,ifItDoesnt,wontItItHapp,wontItItDoesnt};
                Intent intent = new Intent(this, PieChartActivity.class);
                intent.putExtra("results",results);
                intent.putExtra("decisionId",decisionId);
                startActivity(intent);
                break;
        }
    }

    public void startDescriptionActivity(Long id, int square) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("idDecision", id);
        intent.putExtra("squareNumber", square);
        startActivity(intent);
    }

    private void setOnClick() {
        firstSquare.setOnClickListener(this);
        secondSquare.setOnClickListener(this);
        thirdSquare.setOnClickListener(this);
        fourthSquare.setOnClickListener(this);
        calculate_decision.setOnClickListener(this);
    }
}
