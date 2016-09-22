package com.ivart.makedecision.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.BaseApplication;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import io.realm.Realm;

public class MakeDecisionActivity extends Activity {

    EditText decisionQuestion;
    Decision decision;
    Realm realm;
    Long id;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_decision);
        realm = Realm.getDefaultInstance();
        decisionQuestion = (EditText) findViewById(R.id.edt_decision_question);

    }

    public void saveIntoDatabase(final String decisionName) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                decision = realm.createObject(Decision.class);
                id = BaseApplication.productPrimaryKey.getAndIncrement();
                decision.setId(id);
                decision.setmDecisionName(decisionName);
                Intent intent = new Intent(MakeDecisionActivity.this,SquareActivity.class);
                intent.putExtra("decisionId", id);
                startActivity(intent);
                Log.d("LOG",""+id);
            }
        });
    }

    public void onClick(View view) {
        String name;
        name = decisionQuestion.getText().toString();
        if (name.equals("")||name == null) {
            Toast.makeText(MakeDecisionActivity.this, R.string.enter_question, Toast.LENGTH_SHORT).show();
        }else   {
            saveIntoDatabase(name);
            decisionQuestion.getText().clear();
        }
    }
}
