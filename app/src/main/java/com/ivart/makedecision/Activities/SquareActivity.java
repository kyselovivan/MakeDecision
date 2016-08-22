package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ivart.makedecision.R;

public class SquareActivity extends Activity {

    //Long decisionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        Intent intent = getIntent();
        Long decisionId = intent.getLongExtra("decisionId",0);
        Toast.makeText(this,"Decision id : " + decisionId,Toast.LENGTH_SHORT).show();
    }
}
