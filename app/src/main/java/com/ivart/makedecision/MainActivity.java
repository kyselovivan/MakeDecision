package com.ivart.makedecision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ivart.makedecision.Activities.AboutUsActivity;
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
        configToolbar();

        makeDecision = (TextView) findViewById(R.id.btn_make_decision);
        myDecisions = (TextView) findViewById(R.id.btn_get_my_decisions);
        aboutUs = (TextView) findViewById(R.id.btn_about_us);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        }, 2200);
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
        }, 2200);
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
                        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }, 2200);
    }


}
