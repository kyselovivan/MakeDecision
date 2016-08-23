package com.ivart.makedecision.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.BaseApplication;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.Realm;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    EditText description;
    Button addDescription;
    Long decisionId;
    int square;
    Realm realm;
    DecisionDescription decisionDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("idDecision",0L);
        realm = Realm.getDefaultInstance();
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
        String text = description.getText().toString();
        switch (v.getId()){
            case R.id.btn_add_description:
                saveIntoDatabase(decisionId,square,text);
                //Toast.makeText(this,"Decision id = " + decisionId + "\nSquare number = " + square, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void saveIntoDatabase(final Long decisionId, final int square, final String descriptionText) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                decisionDescription = realm.createObject(DecisionDescription.class);
                Long id = BaseApplication.productPrimaryKey.getAndIncrement();
                decisionDescription.setId(id);
                decisionDescription.setDecisionId(decisionId);
                decisionDescription.setSquare(square);
                decisionDescription.setDescriptionText(descriptionText);

                Log.d("LOG","<<<<<<<<<<<<<<< success >>>>>>>>>>>>>>>>\n" +
                "Decision Id = "+ decisionId + "\n" +
                "Decision Description Id = " + id + "\n" +
                "Decision text = " + descriptionText + "\n" +
                "Decision Square = " + square + "\n");
            }
        });
        description.getText().clear();
        Toast.makeText(DescriptionActivity.this,R.string.description_was_added,Toast.LENGTH_LONG).show();
    }
}
