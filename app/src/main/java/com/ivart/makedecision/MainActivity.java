package com.ivart.makedecision;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ivart.makedecision.Activities.AboutUsActivity;
import com.ivart.makedecision.Activities.MakeDecisionActivity;
import com.ivart.makedecision.Activities.MyDecisionsActivity;


public class MainActivity extends Activity implements View.OnClickListener {

    Button makeDecision;
    Button myDecisions;
    Button aboutUs;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        makeDecision = (Button) findViewById(R.id.btn_make_decision);
        myDecisions = (Button) findViewById(R.id.btn_get_my_decisions);
        aboutUs = (Button) findViewById(R.id.btn_about_us);
        makeDecision.setOnClickListener(this);
        myDecisions.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_make_decision:
                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                    intent = new Intent(this, MakeDecisionActivity.class);
                    startActivity(intent,
                            ActivityOptions
                                    .makeSceneTransitionAnimation(this).toBundle());
                } else {
                    intent = new Intent(this, MakeDecisionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_get_my_decisions:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                    intent = new Intent(this, MyDecisionsActivity.class);
                    startActivity(intent,
                            ActivityOptions
                                    .makeSceneTransitionAnimation(this).toBundle());
                } else {
                    intent = new Intent(this, MyDecisionsActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_about_us:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(new Explode());
                    intent = new Intent(this, AboutUsActivity.class);
                    startActivity(intent,
                            ActivityOptions
                                    .makeSceneTransitionAnimation(this).toBundle());
                } else {
                    intent = new Intent(this, AboutUsActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
