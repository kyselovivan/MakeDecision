package com.ivart.makedecision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ivart.makedecision.Activities.AboutTechnique;
import com.ivart.makedecision.Activities.MakeDecisionActivity;
import com.ivart.makedecision.Activities.MyDecisionsActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    TextView makeDecision;
    TextView myDecisions;
    TextView aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeDecision = (TextView) findViewById(R.id.btn_make_decision);
        myDecisions = (TextView) findViewById(R.id.btn_get_my_decisions);
        aboutUs = (TextView) findViewById(R.id.btn_about_us);


    }

    @Override
    public void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        if (makeDecision != null) {
            makeDecision.setEnabled(false);
        }

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        makeDecision.setEnabled(true);
                        Intent intent = new Intent(MainActivity.this, MakeDecisionActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }, 2000);
    }

    public void onClick1(View view) {

        if (myDecisions != null) {
            myDecisions.setEnabled(false);
        }

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        myDecisions.setEnabled(true);
                        Intent intent = new Intent(MainActivity.this, MyDecisionsActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }, 2000);
    }

    public void onClick2(View view) {
        aboutUs = (TextView) findViewById(R.id.btn_about_us);
        if (aboutUs != null) {
            aboutUs.setEnabled(false);
        }

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        aboutUs.setEnabled(true);
                        Intent intent = new Intent(MainActivity.this, AboutTechnique.class);
                        startActivity(intent);
                    }
                });
            }
        }, 2000);
    }


}
