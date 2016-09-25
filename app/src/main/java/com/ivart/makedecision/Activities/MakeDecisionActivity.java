package com.ivart.makedecision.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivart.makedecision.BaseApplication;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import io.realm.Realm;

public class MakeDecisionActivity extends Activity implements View.OnClickListener {

    EditText decisionQuestion;
    Decision decision;
    Realm realm;
    Long id;
    Long editingId;
    com.melnykov.fab.FloatingActionButton add_decision;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_decision);
        Intent intent = getIntent();
        editingId = intent.getLongExtra("editingId", 0);
        realm = Realm.getDefaultInstance();
        decisionQuestion = (EditText) findViewById(R.id.edt_decision_question);
        add_decision = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.btn_add_decision);
        add_decision.setOnClickListener(this);

    }

    public void saveIntoDatabase(final String decisionName) {
        if (editingId != 0) {
            Decision toEdit = realm.where(Decision.class)
                    .equalTo("id", editingId).findFirst();
            realm.beginTransaction();
            toEdit.setmDecisionName(decisionQuestion.getText().toString());
            realm.commitTransaction();
            Intent intent = new Intent(MakeDecisionActivity.this, SquareActivity.class);
            intent.putExtra("decisionId", editingId);
            startActivity(intent);
        } else {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    decision = realm.createObject(Decision.class);
                    id = BaseApplication.productPrimaryKey.getAndIncrement();
                    decision.setId(id);
                    decision.setmDecisionName(decisionName);
                    Intent intent = new Intent(MakeDecisionActivity.this, SquareActivity.class);
                    intent.putExtra("decisionId", id);
                    startActivity(intent);
                    Log.d("LOG", "" + id);
                }
            });
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_decision:
                String name;
                name = decisionQuestion.getText().toString();
                if (name.equals("") || name == null) {
                    Toast.makeText(MakeDecisionActivity.this, R.string.enter_question, Toast.LENGTH_SHORT).show();
                } else {
                    saveIntoDatabase(name);
                    decisionQuestion.getText().clear();
                }
                break;
        }
    }
}
