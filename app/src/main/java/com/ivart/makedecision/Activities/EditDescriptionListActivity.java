package com.ivart.makedecision.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ivart.makedecision.R;

public class EditDescriptionListActivity extends AppCompatActivity {

    Long decisionId;
    int square;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_description_list);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("idDecision",0);
        square = intent.getIntExtra("squareNumber",0);

        Toast.makeText(this,"Decision id ="+decisionId+"\n square = "+ square,Toast.LENGTH_SHORT).show();
    }
}
