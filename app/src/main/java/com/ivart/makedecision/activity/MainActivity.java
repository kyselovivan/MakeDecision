package com.ivart.makedecision.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.ivart.makedecision.R;

public class MainActivity extends AppCompatActivity {

    Button makeDecision;
    Button myDecisions;
    Button aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeDecision =(Button)findViewById(R.id.btn_make_decision);
        myDecisions =(Button)findViewById(R.id.btn_get_my_decisions);
        aboutUs =(Button)findViewById(R.id.btn_about_us);
    }
}
