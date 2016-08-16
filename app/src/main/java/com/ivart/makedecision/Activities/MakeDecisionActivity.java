package com.ivart.makedecision.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

public class MakeDecisionActivity extends AppCompatActivity {

    EditText decisionQuestion;
    Button addDecision;
    Decision decision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_decision);

        decisionQuestion = (EditText)findViewById(R.id.edt_decision_question);
        addDecision = (Button) findViewById(R.id.btn_add_decision);

        addDecision.setOnClickListener(new View.OnClickListener() {
            String name;
            @Override
            public void onClick(View v) {
                name = decisionQuestion.getText().toString();
                if(name.equals("")|| name == null){
                    Toast.makeText(MakeDecisionActivity.this, R.string.enter_question, Toast.LENGTH_SHORT).show();
                }
                else {
                    decision = new Decision(name);
                    Long id = Decision.save(decision);
                    Toast.makeText(MakeDecisionActivity.this,""+id, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
