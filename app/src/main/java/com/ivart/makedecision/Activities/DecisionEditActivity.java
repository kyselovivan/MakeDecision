package com.ivart.makedecision.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ivart.makedecision.R;

public class DecisionEditActivity extends AppCompatActivity implements View.OnClickListener{

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

        setOnClick();
        Toast.makeText(this, ""+decisionId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_will_if_it_happens:
                startEditDescriptionListActivity(decisionId,1);
                break;
            case R.id.btn_will_if_it_doesnt:
                startEditDescriptionListActivity(decisionId,2);
                break;
            case R.id.btn_wont_if_it_happens:
                startEditDescriptionListActivity(decisionId,3);
                break;
            case R.id.btn_wont_if_it_doesnt:
                startEditDescriptionListActivity(decisionId,4);
                break;
        }
    }

    public void startEditDescriptionListActivity(Long id, int square) {
        Intent intent = new Intent(this, EditDescriptionListActivity.class);
        intent.putExtra("idDecision", id);
        intent.putExtra("squareNumber", square);
        startActivity(intent);
    }

    private void setOnClick() {
        firstSquare.setOnClickListener(this);
        secondSquare.setOnClickListener(this);
        thirdSquare.setOnClickListener(this);
        fourthSquare.setOnClickListener(this);
    }
}
