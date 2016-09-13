package com.ivart.makedecision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ivart.makedecision.Activities.AboutUsActivity;
import com.ivart.makedecision.Activities.MakeDecisionActivity;
import com.ivart.makedecision.Activities.MyDecisionsActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener {

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
        makeDecision.setOnClickListener(this);
        myDecisions.setOnClickListener(this);
        aboutUs.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_make_decision:
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
                }, 2200);
                break;
            case R.id.btn_get_my_decisions:
                if (myDecisions != null) {
                    myDecisions.setEnabled(false);
                }

                Timer buttonTimer1 = new Timer();
                buttonTimer1.schedule(new TimerTask() {

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
                }, 2200);
                break;
            case R.id.btn_about_us:
                if (aboutUs != null) {
                    aboutUs.setEnabled(false);
                }

                Timer buttonTimer2 = new Timer();
                buttonTimer2.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                aboutUs.setEnabled(true);
                                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }, 2200);

                break;
        }
    }
}
