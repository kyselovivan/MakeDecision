package com.ivart.makedecision.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.ivart.makedecision.R;

public class DecisionEditActivity extends AppCompatActivity {

    Long decisionId;
    Button firstSquare;
    Button secondSquare;
    Button thirdSquare;
    Button fourthSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("decisionId", 0);

        firstSquare = (Button) findViewById(R.id.btn_will_if_it_happens);
        secondSquare = (Button) findViewById(R.id.btn_will_if_it_doesnt);
        thirdSquare = (Button) findViewById(R.id.btn_wont_if_it_happens);
        fourthSquare = (Button) findViewById(R.id.btn_wont_if_it_doesnt);

        Toast.makeText(this, ""+decisionId, Toast.LENGTH_SHORT).show();
    }
}
