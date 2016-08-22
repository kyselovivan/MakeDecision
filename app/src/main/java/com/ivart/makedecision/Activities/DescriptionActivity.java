package com.ivart.makedecision.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.R;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    EditText description;
    Button addDescription;
    Long decisionId;
    int square;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("idDecision",0L);
        square = intent.getIntExtra("squareNumber",0);
        description = (EditText)findViewById(R.id.edt_decision_description);
        addDescription = (Button)findViewById(R.id.btn_add_description);

        setOnClick();

    }

    private void setOnClick() {
        addDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_description:
                Toast.makeText(this,"Decision id = " + decisionId + "\nSquare number = " + square, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
