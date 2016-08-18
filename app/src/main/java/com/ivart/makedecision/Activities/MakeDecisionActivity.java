package com.ivart.makedecision.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import io.realm.Realm;

public class MakeDecisionActivity extends AppCompatActivity {

    EditText decisionQuestion;
    Button addDecision;
    Decision decision;
    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_decision);

        decisionQuestion = (EditText) findViewById(R.id.edt_decision_question);
        addDecision = (Button) findViewById(R.id.btn_add_decision);

        addDecision.setOnClickListener(new View.OnClickListener() {
            String name;
            @Override
            public void onClick(View v) {
                name = decisionQuestion.getText().toString();
                if (name.equals("")||name == null) {
                    Toast.makeText(MakeDecisionActivity.this, R.string.enter_question, Toast.LENGTH_SHORT).show();
                } else {
                    saveIntoDatabase(name);
                }
            }
        });
    }

    private void saveIntoDatabase(final String desisionName) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                decision = bgRealm.createObject(Decision.class);
                decision.setmDecisionName(desisionName);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v("LOG",">>>>>>>>>> success <<<<<<<<<<<<");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });
    }

}
