package com.ivart.makedecision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ivart.makedecision.Activities.AboutUsActivity;
import com.ivart.makedecision.Activities.MakeDecisionActivity;
import com.ivart.makedecision.Activities.MyDecisionsActivity;

import java.util.concurrent.atomic.AtomicLong;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        makeDecision.setOnClickListener(this);
        myDecisions.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_make_decision:
                Intent intent = new Intent(this,MakeDecisionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_get_my_decisions:
                intent = new Intent(this,MyDecisionsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_about_us:
                intent = new Intent(this,AboutUsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
